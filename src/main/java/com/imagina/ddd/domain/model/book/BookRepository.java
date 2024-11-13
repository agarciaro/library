package com.imagina.ddd.domain.model.book;

import java.util.List;
import java.util.Optional;

import com.imagina.ddd.domain.model.author.AuthorId;

import lombok.Value;

public interface BookRepository {
	
	List<Book> findBy(BookQuery query);
	
	void save(Book book);
	
	Optional<Book> findById(BookId id);
	
	@Value
	class BookQuery {
		BookName name;
		AuthorId authorId;
		ISBN isbn;

		public BookQuery(BookName name, AuthorId authorId, ISBN isbn) {
			if (name == null && authorId == null && isbn == null) {
				throw new IllegalArgumentException("At least one parameter should be provided");
			}
			this.name = name;
			this.authorId = authorId;
			this.isbn = isbn;
		}

		public Optional<BookName> getName() {
			return Optional.ofNullable(name);
		}

		public Optional<AuthorId> getAuthorId() {
			return Optional.ofNullable(authorId);
		}

		public Optional<ISBN> getIsbn() {
			return Optional.ofNullable(isbn);
		}
	}
}
