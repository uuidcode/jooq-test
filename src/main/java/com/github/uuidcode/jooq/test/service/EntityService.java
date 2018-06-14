package com.github.uuidcode.jooq.test.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DefaultDSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.jooq.test.annotation.Id;
import com.github.uuidcode.jooq.test.util.CoreUtil;
import com.google.common.base.CaseFormat;

@Service
@Transactional
public class EntityService<CHILD> {
    protected static Logger logger = LoggerFactory.getLogger(EntityService.class);

    @Autowired
    protected DefaultDSLContext dsl;

    public <PARENT> void join(List<PARENT> list) {
        if (list == null) {
            return;
        }

        if (list.size() == 0) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(">>> join list: {}", CoreUtil.toJson(list));
        }

        Class<PARENT> parentClass = (Class<PARENT>) list.get(0).getClass();
        Class<CHILD> childClass = (Class<CHILD>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
        String idName = this.getIdName(parentClass);
        Method parentIdMethod = this.getIdMethod(parentClass, idName);
        parentIdMethod.setAccessible(true);
        Method childIdMethod = this.getIdMethod(childClass, idName);
        childIdMethod.setAccessible(true);

        List<Long> idList = list.stream()
            .map(this.createIdFunction(parentIdMethod))
            .collect(Collectors.toList());

        if (logger.isDebugEnabled()) {
            logger.debug(">>> join idList: {}", CoreUtil.toJson(idList));
        }

        String parentSimpleName = parentClass.getSimpleName();
        String childSimpleName = childClass.getSimpleName();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> join parentSimpleName: {}", CoreUtil.toJson(parentSimpleName));
            logger.debug(">>> join childSimpleName: {}", CoreUtil.toJson(childSimpleName));
        }

        String childQClassName = "com.github.uuidcode.jooq.test.jooq.tables.Q" + childSimpleName;

        Table table;
        TableField idTableField;

        try {
            Class<?> qClass = Class.forName(childQClassName);
            table = (Table) qClass.newInstance();
            String columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, idName);
            columnName = columnName.toUpperCase();
            Field field = table.getClass().getDeclaredField(columnName);
            field.setAccessible(true);
            idTableField = (TableField) field.get(table);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }


        List<CHILD> childAllList = this.dsl.selectFrom(table)
            .where(idTableField.in(idList))
            .fetch()
            .into(childClass);

        Map<Long, List<CHILD>> map = childAllList.stream()
            .collect(Collectors.groupingBy(this.createIdFunction(childIdMethod)));

        if (logger.isDebugEnabled()) {
            logger.debug(">>> join map: {}", CoreUtil.toJson(map));
        }

        list.forEach(parent -> {
            Function<Object, Long> idFunction = this.createIdFunction(parentIdMethod);
            Long id = idFunction.apply(parent);
            List<CHILD> childList = map.get(id);

            try {
                String setMethodName = "set" + childSimpleName + "List";

                if (logger.isDebugEnabled()) {
                    logger.debug(">>> join setMethodName: {}", CoreUtil.toJson(setMethodName));
                }

                Method setMethod = parentClass.getDeclaredMethod(setMethodName, List.class);

                if (setMethod != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(">>> join setMethod");
                    }
                }

                setMethod.invoke(parent, childList);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }

        });
    }

    private <T> Function<T, Long> createIdFunction(Method idMethod) {
        return object -> {
            try {
                return (Long) idMethod.invoke(object);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        };
    }

    public String getIdName(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> field.getAnnotation(Id.class) != null)
            .findFirst()
            .map(Field::getName)
            .orElse(null);
    }

    public Method getIdMethod(Class clazz, String fieldName) {
        try {
            String getMethodName = "get" + CoreUtil.toFirstCharUpperCase(fieldName);

            if (logger.isDebugEnabled()) {
                logger.debug(">>> getIdMethod clazz: {}", CoreUtil.toJson(clazz.getName()));
                logger.debug(">>> getIdMethod getMethodName: {}", CoreUtil.toJson(getMethodName));
            }

            return clazz.getDeclaredMethod(getMethodName);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
