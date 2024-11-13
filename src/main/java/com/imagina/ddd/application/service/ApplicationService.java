package com.imagina.ddd.application.service;

import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@AllArgsConstructor
public abstract class ApplicationService {

	@NonNull
	protected final TransactionTemplate transactionTemplate;

	protected ApplicationService(PlatformTransactionManager platformTransactionManager) {
		this(new TransactionTemplate(platformTransactionManager));
	}

	protected void executeInTransaction(Runnable method) {
		transactionTemplate.execute(status -> {
			method.run();
			return true;
		});
	}

	protected <T> T executeInTransactionAndReturn(Supplier<T> method) {
		return transactionTemplate.execute(status -> method.get());
	}
}