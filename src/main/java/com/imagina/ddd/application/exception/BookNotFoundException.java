package com.imagina.ddd.application.exception;

import com.imagina.ddd.domain.model.book.BookId;

import lombok.Value;

@Value
public class BookNotFoundException extends RuntimeException {
	BookId bookId;
}
