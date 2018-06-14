package com.github.uuidcode.jooq.test.entity;

import java.util.Date;
import java.util.List;

import com.github.uuidcode.jooq.test.annotation.Id;

public class User {
    @Id
    private Long userId;
    private String name;
    private Date regDatetime;
    private List<Book> bookList;

    public List<Book> getBookList() {
        return this.bookList;
    }

    public User setBookList(List<Book> bookList) {
        this.bookList = bookList;
        return this;
    }

    public Date getRegDatetime() {
        return this.regDatetime;
    }

    public User setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
        return this;
    }
    public String getName() {
        return this.name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
