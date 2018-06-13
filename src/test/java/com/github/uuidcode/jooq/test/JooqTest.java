package com.github.uuidcode.jooq.test;

import java.sql.Timestamp;
import java.util.Date;

import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.jooq.test.entity.User;
import com.github.uuidcode.jooq.test.jooq.tables.QUser;
import com.github.uuidcode.jooq.test.util.CoreUtil;

public class JooqTest extends CoreTest {
    @Autowired
    private DefaultDSLContext dsl;

    @Test
    public void insert() {
        int row = this.dsl.insertInto(QUser.USER, QUser.USER.NAME, QUser.USER.REG_DATETIME)
            .values(CoreUtil.createUUID(), new Timestamp(new Date().getTime()))
            .execute();

        this.printJson(row);
    }
    
    @Test
    public void insertSet() {
        int row = this.dsl.insertInto(QUser.USER)
            .set(QUser.USER.NAME, CoreUtil.createUUID())
            .set(QUser.USER.REG_DATETIME, DSL.currentTimestamp())
            .execute();

        this.printJson(row);
    }

    @Test
    public void update() {
        this.dsl.update(QUser.USER)
            .set(QUser.USER.REG_DATETIME, DSL.currentTimestamp())
            .where(QUser.USER.USER_ID.eq(1L))
            .execute();
    }

    @Test
    public void select() {
        User user = this.dsl.selectFrom(QUser.USER)
            .where(QUser.USER.USER_ID.eq(1L))
            .fetchOne()
            .into(User.class);

        this.printJson(user);
    }
}