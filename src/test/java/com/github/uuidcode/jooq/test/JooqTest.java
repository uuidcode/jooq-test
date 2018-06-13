package com.github.uuidcode.jooq.test;

import java.sql.Timestamp;
import java.util.Date;

import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.jooq.test.jooq.tables.User;
import com.github.uuidcode.jooq.test.jooq.tables.records.UserRecord;
import com.github.uuidcode.jooq.test.util.CoreUtil;

public class JooqTest extends CoreTest {
    @Autowired
    private DefaultDSLContext dsl;

    @Test
    public void insert() {
        int row = this.dsl.insertInto(User.USER, User.USER.NAME, User.USER.REG_DATETIME)
            .values(CoreUtil.createUUID(), new Timestamp(new Date().getTime()))
            .execute();

        this.printJson(row);
    }
    
    @Test
    public void insertSet() {
        int row = this.dsl.insertInto(User.USER)
            .set(User.USER.NAME, CoreUtil.createUUID())
            .set(User.USER.REG_DATETIME, DSL.currentTimestamp())
            .execute();

        this.printJson(row);
    }

    @Test
    public void insertReturn() {
        UserRecord userRecord = this.dsl.selectFrom(User.USER)
            .where(User.USER.USER_ID.eq(1L))
            .fetchOne();

        this.printJson(userRecord);

    }
}