package com.imagina.ddd.application.task;

public interface TaskHandler<D extends TaskData> {

	boolean handles(TaskType<D> taskType);

	void handle(D taskData);
}
