package com.assignment.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	Logger log = LoggerFactory.getLogger(TransactionController.class);

	public TransactionController(TransactionRepository transactionRepository, RewardServiceImpl rewardService) {
		this.transactionRepository = transactionRepository;
		this.rewardService = rewardService;
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To get TransactionBycustomer data from D/B
	 * @since 1.0
	 * @param categoryId
	 * @return  
	 */

	@GetMapping("/{customerId}")
	public List<Transaction> getTransactionsByCustomer(@PathVariable Long customerId) {

		log.info("entering the request for getTransactions with customerId{} ", customerId);
		List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException("No transactions found for customer ID " + customerId);
		}
		log.info("complete the request for getTransactions with customerId{} ", customerId);
		return transactions;
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To create transaction.
	 * @since 1.0
	 * @param Transaction @return 
	 */
	@PostMapping("/")
	public Transaction addTransaction(@RequestBody Transaction transaction) {
		log.info("entering the request for add new Transaction");
		if (transaction.getAmount() <= 0) {

			throw new IllegalArgumentException("transaction amount must be greater than 0");
		}

		Transaction save = transactionRepository.save(transaction);
		rewardService.saveRewardPoints(save);
		log.info("complete the request for add new Transaction");
		return save;

	}

}
