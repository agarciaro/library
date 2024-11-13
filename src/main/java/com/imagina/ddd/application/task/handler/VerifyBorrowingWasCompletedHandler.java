package com.imagina.ddd.application.task.handler;

import java.util.Optional;
import org.springframework.transaction.PlatformTransactionManager;

import com.imagina.ddd.application.exception.BookNotFoundException;
import com.imagina.ddd.application.service.ApplicationService;
import com.imagina.ddd.application.task.TaskHandler;
import com.imagina.ddd.application.task.TaskType;
import com.imagina.ddd.application.task.data.VerifyBorrowingWasCompletedData;
import com.imagina.ddd.domain.model.book.Book;
import com.imagina.ddd.domain.model.book.BookRepository;
import com.imagina.ddd.domain.model.book.Borrowing;
import com.imagina.ddd.domain.model.user.UserManager;

public class VerifyBorrowingWasCompletedHandler extends ApplicationService
		implements TaskHandler<VerifyBorrowingWasCompletedData> {
	private final UserManager userManager;
	private final BookRepository bookRepository;

	public VerifyBorrowingWasCompletedHandler(PlatformTransactionManager platformTransactionManager,
			UserManager userManager, BookRepository bookRepository) {
		super(platformTransactionManager);
		this.userManager = userManager;
		this.bookRepository = bookRepository;
	}

	@Override
	public boolean handles(TaskType<VerifyBorrowingWasCompletedData> taskType) {
		return TaskType.VERIFY_BORROWING_WAS_COMPLETED.equals(taskType);
	}

	@Override
	public void handle(VerifyBorrowingWasCompletedData taskData) {
		executeInTransaction(() -> {
			Optional<Book> bookOpt = bookRepository.findById(taskData.getBookId());
			if (bookOpt.isEmpty()) {
				throw new BookNotFoundException(taskData.getBookId());
			}
			Book book = bookOpt.get();
			Borrowing borrowing = book.getBorrowingOrThrow(taskData.getBorrowingId());
			if (borrowing.isOverdue()) {
				userManager.banUser(borrowing.getUserId());
				book.declareLost(taskData.getBorrowingId());
				bookRepository.save(book);
			}
		});
	}
}
