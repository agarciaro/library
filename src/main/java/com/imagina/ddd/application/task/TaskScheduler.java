package com.imagina.ddd.application.task;

import java.time.Clock;
import java.time.Instant;

public interface TaskScheduler {
	<D extends TaskData> void schedule(TaskType<D> taskType, D taskData, Instant scheduleAt);

	default <D extends TaskData> void schedule(TaskType<D> taskType, D taskData) {
		schedule(taskType, taskData, Instant.now(Clock.systemUTC()));
	}
}
