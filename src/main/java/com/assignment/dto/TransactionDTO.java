package com.assignment.dto;

import com.assignment.model.Transaction;

public class TransactionDTO {

	private Transaction transaction;

	private int rewardPoints;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public TransactionDTO(Transaction transaction, int rewardPoints) {

		this.transaction = transaction;
		this.rewardPoints = rewardPoints;

	}

}
