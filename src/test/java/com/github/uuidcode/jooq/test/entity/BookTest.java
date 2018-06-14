package com.github.uuidcode.jooq.test.entity;

import java.util.List;

import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.jooq.test.CoreTest;
import com.github.uuidcode.jooq.test.service.BookService;
import com.github.uuidcode.jooq.test.util.CoreUtil;

import static com.github.uuidcode.jooq.test.jooq.tables.QBook.BOOK;
import static com.github.uuidcode.jooq.test.jooq.tables.QUser.USER;

public class BookTest extends CoreTest {
    @Autowired
    private DefaultDSLContext dsl;

    @Autowired
    private BookService bookService;

    @Test
    public void insert() {
        this.dsl.insertInto(BOOK)
            .set(BOOK.NAME, CoreUtil.createUUID())
            .set(BOOK.USER_ID, 2L)
            .execute();
    }

    @Test
    public void oneToMany() {
        List<User> userList = this.dsl.selectFrom(USER)
            .fetch()
            .into(User.class);

        this.bookService.join(userList);

        this.printJson(userList);
    }
}