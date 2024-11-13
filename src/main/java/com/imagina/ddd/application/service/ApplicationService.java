package com.imagina.ddd.application.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.function.Supplier;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.AllArgsConstructor;
import lombok.NonNull;

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
