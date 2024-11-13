package com.imagina.ddd.domain.model.book;

import java.util.ArrayList;
import java.util.List;

import com.imagina.ddd.domain.model.author.AuthorId;
import com.imagina.ddd.domain.model.user.User;
import com.imagina.ddd.domain.model.user.UserStatus;

import lombok.Getter;

@Getter
public class Book {
	
	private final BookId id;
	private final BookName bookName;
	private final ISBN isbn;
	private final AuthorId authorId;
	private final List<Borrowing> borrowings;
	private BookStatus status;
	
	public Book(BookName bookName, ISBN isbn, AuthorId authorId) {
		this.id = BookId.generate();
		this.bookName = bookName;
		this.isbn = isbn;
		this.authorId = authorId;
		this.status = BookStatus.AVAILABLE;
		this.borrowings = new ArrayList<>();
	}
	
	public Borrowing borrow(User user) {
		if (this.status != BookStatus.AVAILABLE) {
			throw new IllegalStateException("Book is not available for borrowing");
		}
		
		if (user.getStatus() == UserStatus.BANNED) {
			throw new IllegalStateException("User is banned");
		}
		
		Borrowing borrowing = new Borrowing(this.id, user.getId());
		updateStatus(BookStatus.BORROWED);
		this.borrowings.add(borrowing);
		
		return borrowing;
	}
	
	public void returnBook(BorrowingId borrowingId) {
		Borrowing borrowing = getBorrowingOrThrow(borrowingId);
		borrowing.returnBook();
		updateStatus(BookStatus.AVAILABLE);
	}
	
	public void declareLost(BorrowingId borrowingId) {
		getBorrowingOrThrow(borrowingId);
		updateStatus(BookStatus.LOST);
	}
	
	public void declareDestroyed(BorrowingId borrowingId) {
		getBorrowingOrThrow(borrowingId);
		updateStatus(BookStatus.DESTROYED);
	}
	
	private Borrowing getBorrowingOrThrow(BorrowingId borrowingId) {
		return borrowings.stream().filter(b -> b.getId().equals(borrowingId)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Borrowing not found"));
	}

	private void updateStatus(BookStatus bookStatus) {
		if (!this.status.canMoveTo(bookStatus)) {
			throw new IllegalStateException("Invalid status change");
		}
		this.status = bookStatus;
	}
	
}
