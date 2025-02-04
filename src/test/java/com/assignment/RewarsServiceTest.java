package com.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.assignment.model.Customer;
import com.assignment.model.Transaction;
import com.assignment.repository.RewardRepository;
import com.assignment.service.RewardServiceImpl;

@SpringBootTest
public class RewarsServiceTest {

	@Mock
	private RewardRepository rewardRepository;

	@Mock
	private Transaction transaction;

	@Mock
	private Customer customer;

	@InjectMocks
	private RewardServiceImpl rewardService;

	@BeforeEach
	void setup() {
		rewardService = new RewardServiceImpl(rewardRepository);
	}

	// Test calculateRewardPoints()
	@Test
	void testCalculateRewardPoints_NoReward() {
		when(transaction.getAmount()).thenReturn(40.0);
		int points = rewardService.calculateRewardPoints(transaction);
		assertEquals(0, points);
	}

	@Test
	void testCalculateRewardPoints_TwoThresholds() {
		when(transaction.getAmount()).thenReturn(120.0);
		int points = rewardService.calculateRewardPoints(transaction);
		assertEquals(90, points); // (20*2) + (50) = 40 + 50 = 90
	}

	@Test
	void testCalculateRewardPoints_Exactly100() {
		when(transaction.getAmount()).thenReturn(100.0);
		int points = rewardService.calculateRewardPoints(transaction);
		assertEquals(50, points); // (100-50) = 50
	}

	@Test
	void testCalculateRewardPoints_Exactly50() {
		when(transaction.getAmount()).thenReturn(50.0);
		int points = rewardService.calculateRewardPoints(transaction);
		assertEquals(0, points);
	}

	// Test saveRewardPoints()
	@Test
	void testSaveRewardPoints() {
		when(transaction.getAmount()).thenReturn(120.0);
		when(transaction.getTransactionDate()).thenReturn(LocalDate.of(2024, 2, 1));
		when(transaction.getCustomer()).thenReturn(customer);
		when(customer.getId()).thenReturn(1L);

		rewardService.saveRewardPoints(transaction);

		verify(rewardRepository, times(1)).save(argThat(reward -> reward.getCustomerId().equals(1L)
				&& reward.getMonth() == 2 && reward.getYear() == 2024 && reward.getPoints() == 90));
	}

}
