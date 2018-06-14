package com.github.uuidcode.jooq.test;

import java.sql.Timestamp;
import java.util.Date;

import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.jooq.test.entity.User;
import com.github.uuidcode.jooq.test.util.CoreUtil;

import static com.github.uuidcode.jooq.test.jooq.tables.QUser.USER;

public class JooqTest extends CoreTest {
    @Autowired
    private DefaultDSLContext dsl;

    @Test
    public void insert() {
        int row = this.dsl.insertInto(USER, USER.NAME, USER.REG_DATETIME)
            .values(CoreUtil.createUUID(), new Timestamp(new Date().getTime()))
            .execute();

        this.printJson(row);
    }
    
    @Test
    public void insertSet() {
        int row = this.dsl.insertInto(USER)
            .set(USER.NAME, CoreUtil.createUUID())
            .set(USER.REG_DATETIME, DSL.currentTimestamp())
            .execute();

        this.printJson(row);
    }

    @Test
    public void update() {
        this.dsl.update(USER)
            .set(USER.REG_DATETIME, DSL.currentTimestamp())
            .where(USER.USER_ID.eq(1L))
            .execute();
    }

    @Test
    public void select() {
        User user = this.dsl.selectFrom(USER)
            .where(USER.USER_ID.eq(1L))
            .fetchOne()
            .into(User.class);

        this.printJson(user);
    }
}