/*
 * This file is generated by jOOQ.
 */
package com.github.uuidcode.jooq.test.jooq;


import com.github.uuidcode.jooq.test.jooq.tables.QBook;
import com.github.uuidcode.jooq.test.jooq.tables.QUser;
import com.github.uuidcode.jooq.test.jooq.tables.records.QBookRecord;
import com.github.uuidcode.jooq.test.jooq.tables.records.QUserRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>jooq</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<QBookRecord, Long> IDENTITY_BOOK = Identities0.IDENTITY_BOOK;
    public static final Identity<QUserRecord, Long> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<QBookRecord> KEY_BOOK_PRIMARY = UniqueKeys0.KEY_BOOK_PRIMARY;
    public static final UniqueKey<QUserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<QBookRecord, Long> IDENTITY_BOOK = Internal.createIdentity(QBook.BOOK, QBook.BOOK.BOOK_ID);
        public static Identity<QUserRecord, Long> IDENTITY_USER = Internal.createIdentity(QUser.USER, QUser.USER.USER_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<QBookRecord> KEY_BOOK_PRIMARY = Internal.createUniqueKey(QBook.BOOK, "KEY_book_PRIMARY", QBook.BOOK.BOOK_ID);
        public static final UniqueKey<QUserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(QUser.USER, "KEY_user_PRIMARY", QUser.USER.USER_ID);
    }
}
