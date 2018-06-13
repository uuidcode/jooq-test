package com.github.uuidcode.jooq.test.entity;

import java.util.Date;

public class User {
    private Long userId;
    private String name;
    private Date regDatetime;

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
