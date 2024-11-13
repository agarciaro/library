package com.imagina.ddd.application.task.data;

import com.imagina.ddd.application.task.TaskData;
import com.imagina.ddd.domain.model.book.BookId;
import com.imagina.ddd.domain.model.book.BorrowingId;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerifyBorrowingWasCompletedData implements TaskData {
	private final BookId bookId;
	private final BorrowingId borrowingId;
}
