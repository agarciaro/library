package com.imagina.ddd.domain.model.book;

import java.time.Clock;
import java.time.Instant;

import com.imagina.ddd.domain.model.user.UserId;

import lombok.Getter;

@Getter
public class Borrowing {
	
	private final BorrowingId id;
	private final BookId bookId;
	private final UserId userId;
	private final Instant borrowedAt;
	private final Instant returnedDueAt;
	private Instant returnedAt;
	
	
	public Borrowing(BookId bookId, UserId userId) {
		this.id = BorrowingId.generate();
		this.bookId = bookId;
		this.userId = userId;
		this.borrowedAt = Instant.now(Clock.systemUTC());
		this.returnedDueAt = borrowedAt.plusSeconds(60 * 60 * 24 * 60);
	}
	
	public void returnBook() {
		this.returnedAt = Instant.now(Clock.systemUTC());
	}
	
	public boolean isOverdue() {
		return !wasReturned() && Instant.now(Clock.systemUTC()).isAfter(returnedDueAt);
	}

	private boolean wasReturned() {
		return returnedAt != null;
	}
	
}
