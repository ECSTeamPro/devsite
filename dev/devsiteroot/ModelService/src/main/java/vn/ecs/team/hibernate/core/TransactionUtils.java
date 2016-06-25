package vn.ecs.team.hibernate.core;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionUtils<KEY, T> {

	Logger logger = LoggerFactory.getLogger(TransactionUtils.class);
	BaseQuery<KEY, T> baseQuery;
	boolean isTransaction = false;
	Transaction trans;

	public TransactionUtils(BaseQuery<KEY, T> baseQuery) {
		// TODO Auto-generated constructor stub
		this.baseQuery = baseQuery;
	}

	public void startTransaction() {
		if (isTransaction)
			return;
		trans = baseQuery.getSession().beginTransaction();
		if (!trans.isActive()) {
			trans.begin();
			isTransaction = true;
		}
	}

	public void commit() {
		if (!isTransaction) {
			logger.debug("commit transaction");
			trans = baseQuery.getSession().getTransaction();
			if (trans.isActive())
				trans.commit();
			isTransaction = false;
		}
	}

	public void rollback() {
		if (!isTransaction) {
			logger.debug("rollback transaction");
			trans = baseQuery.getSession().getTransaction();
			trans.rollback();
			isTransaction = false;
		}
	}
	
	public Transaction getTransaction(){
		return trans;
	}

}
