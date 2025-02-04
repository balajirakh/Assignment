package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.Transaction;
import com.assignment.repository.TransactionRepository;
import com.assignment.service.RewardServiceImpl;

	
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private RewardServiceImpl rewardService;

	public TransactionController(TransactionRepository transactionRepository, RewardServiceImpl rewardService) {
		this.transactionRepository = transactionRepository;
		this.rewardService = rewardService;
	}

	@GetMapping("/{customerId}")
	public List<Transaction> getTransactionsByCustomer(@PathVariable Long customerId) {
		List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException("No transactions found for customer ID " + customerId);
		}
		return transactions;
	}

	@PostMapping("/")
	public Transaction addTransaction(@RequestBody Transaction transaction) {

		if (transaction.getAmount() <= 0) {

			throw new IllegalArgumentException("transaction amount must be greater than 0");
		}

		Transaction save = transactionRepository.save(transaction);
		rewardService.saveRewardPoints(save);
		return save;

	}

}
