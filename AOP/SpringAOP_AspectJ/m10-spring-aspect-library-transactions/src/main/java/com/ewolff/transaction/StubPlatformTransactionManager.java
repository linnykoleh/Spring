package com.ewolff.transaction;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class StubPlatformTransactionManager extends
		AbstractPlatformTransactionManager {

	private int commit = 0;
	private int rollback = 0;
	private int total;
	private boolean active;
	private boolean rollbackOnly;

	@Override
	protected boolean isExistingTransaction(Object transaction)
			throws TransactionException {
		return active;
	}

	public void reset() {
		commit = rollback = total = 0;
		rollbackOnly = false;
		active = false;
	}

	public int getCommit() {
		return commit;
	}

	public int getRollback() {
		return rollback;
	}

	@Override
	protected Object doGetTransaction() throws TransactionException {
		return new Object();
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition)
			throws TransactionException {
		active = true;
	}

	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status)
			throws TransactionException {
		rollbackOnly = true;
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status)
			throws TransactionException {
		if (rollbackOnly) {
			doRollback(status);
			rollbackOnly = false;
		} else {
			commit++;
			total++;
		}
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status)
			throws TransactionException {
		rollback++;
		total++;
	}

	public int getTotal() {
		return total;
	}

}
