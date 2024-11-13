package com.imagina.ddd.application.task;

import com.imagina.ddd.application.task.data.VerifyBorrowingWasCompletedData;

import lombok.Value;

@Value
public class  TaskType<D extends TaskData> {
	public static final TaskType<VerifyBorrowingWasCompletedData> VERIFY_BORROWING_WAS_COMPLETED =
			new TaskType<>("VERIFY_BORROWING_WAS_COMPLETED", VerifyBorrowingWasCompletedData.class);

	String name;
	Class<D> dataType;

	private TaskType(String name, Class<D> dataType) {
		this.name = name;
		this.dataType = dataType;
	}
}
