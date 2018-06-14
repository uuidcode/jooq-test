package com.github.uuidcode.jooq.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.jooq.test.entity.Book;

@Service
@Transactional
public class BookService extends EntityService<Book> {
}
