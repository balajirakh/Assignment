package com.assignment.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.model.RewardPoints;
import com.assignment.model.Transaction;
import com.assignment.repository.RewardRepository;

@Service
public class RewardServiceImpl {
	@Autowired
	private  RewardRepository rewardRepository;



	public RewardServiceImpl(RewardRepository rewardRepository) {
		this.rewardRepository = rewardRepository;
	}

	public int calculateRewardPoints(Transaction transaction) {
		double amount = transaction.getAmount();
		int points = 0;

		if (amount > 100) {
			points += (int)(amount - 100) * 2;
			
		}
		if (amount > 50) {
			
			
			points +=(int)Math.min(amount,100)-50;
		}

		return points;
	}

	public  void saveRewardPoints(Transaction transaction) {
		int points = calculateRewardPoints(transaction);
		LocalDate date = transaction.getTransactionDate();

		RewardPoints reward = new RewardPoints(null, transaction.getCustomer().getId(), date.getMonthValue(),
				date.getYear(), points);

		rewardRepository.save(reward); 
	}

	public List<RewardPoints> getCustomerRewards(Long CustomerId) {

		return rewardRepository.findByCustomerId(CustomerId);

	}
	
}
