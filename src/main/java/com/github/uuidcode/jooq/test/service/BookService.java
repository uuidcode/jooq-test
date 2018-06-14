package com.github.uuidcode.jooq.test.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.jooq.test.entity.Book;
import com.github.uuidcode.jooq.test.entity.User;

import static com.github.uuidcode.jooq.test.jooq.tables.QBook.BOOK;

@Service
@Transactional
public class BookService {
    @Autowired
    private DefaultDSLContext dsl;

    public void join(List<User> userList) {
        List<Long> userIdList = userList.stream()
            .map(User::getUserId)
            .collect(Collectors.toList());

        List<Book> bookList = this.dsl.selectFrom(BOOK)
            .where(BOOK.USER_ID.in(userIdList))
            .fetch()
            .into(Book.class);

        Map<Long, List<Book>> map = bookList.stream()
            .collect(Collectors.groupingBy(Book::getUserId));

        userList.forEach(user -> {
            List<Book> currentBookList = map.get(user.getUserId());
            user.setBookList(currentBookList);
        });
    }
}
