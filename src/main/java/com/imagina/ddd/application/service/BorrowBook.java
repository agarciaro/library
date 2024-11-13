package com.imagina.ddd.application.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import com.imagina.ddd.application.exception.BookNotFoundException;
import com.imagina.ddd.application.exception.UserNotFoundException;
import com.imagina.ddd.domain.model.book.Book;
import com.imagina.ddd.domain.model.book.BookId;
import com.imagina.ddd.domain.model.book.BookRepository;
import com.imagina.ddd.domain.model.book.Borrowing;
import com.imagina.ddd.domain.model.user.User;
import com.imagina.ddd.domain.model.user.UserId;
import com.imagina.ddd.domain.model.user.UserManager;

public class BorrowBook extends ApplicationService {
	
	private final TaskScheduler taskScheduler;
	private final BookRepository bookRepository;
	private final UserManager userManager;
	
	@Autowired
	protected BorrowBook(PlatformTransactionManager platformTransactionManager, TaskScheduler taskScheduler,
			BookRepository bookRepository, UserManager userManager) {
		super(platformTransactionManager);
		this.taskScheduler = taskScheduler;
		this.bookRepository = bookRepository;
		this.userManager = userManager;
	}

	public <T> T borrow(BookId bookId, UserId userId, Function<Borrowing, T> mapper) {
		Optional<User> userOpt = userManager.getUser(userId);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException(userId);
		}
		User user = userOpt.get();
		
		return executeInTransactionAndReturn(() -> {
			Optional<Book> bookOpt = bookRepository.findById(bookId);
			if (bookOpt.isEmpty()) {
				throw new BookNotFoundException(bookId);
			}
			Book book = bookOpt.get();
			Borrowing borrowing = book.borrow(user);
			bookRepository.save(book);
			taskScheduler.schedule(TaskType.VERIFY_BORROWING_WAS_COMPLETED,
					new VerifyBorrowingWasCompletedData(bookOpt.get().getId(), borrowing.getId()),
					borrowing.getReturnedDueAt());
			return mapper.apply(borrowing);
		});
	}
	

}
