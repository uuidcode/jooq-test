package com.github.uuidcode.jooq.test.entity;

public class Book {
    private Long bookId;
    private String name;
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public Book setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public String getName() {
        return this.name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }
    public Long getBookId() {
        return this.bookId;
    }

    public Book setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }
}
