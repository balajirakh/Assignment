package com.assignment.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.MonthlyRewardSummaryDTO;
import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.RewardPoints;
import com.assignment.service.RewardServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {
	@Autowired
	private RewardServiceImpl rewardService;

	Logger log = LoggerFactory.getLogger(RewardPoints.class);

	public RewardController(RewardServiceImpl rewardService) {
		this.rewardService = rewardService;
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To get customer Rewardspoints.
	 * @param categoryId
	 * @since 1.0
	 * @return  
	 */
	@GetMapping("/{customerId}")
	public List<RewardPoints> getCustomerRewards(@PathVariable Long customerId) {

		log.info("entering the request for getcustomrRewardsDetails with customerId{} ", customerId);
		List<RewardPoints> rewards = rewardService.getCustomerRewards(customerId);

		if (rewards.isEmpty()) {

			throw new ResourceNotFoundException("no rewards points found for customer Id " + customerId);
		}

		log.info("complete the request for getcustomrRewardsDetails with customerId{} ", customerId);
		return rewards;
	}

	/**
	 * @author Balaji Rakh
	 * @apiNote To get getCustomerMonthlyRewardsSummary data.
	 * @since 1.0
	 * @param categoryId
	 * @return  
	 */

	@GetMapping("/monthly-rewards-summary/{customerId}")
	public ResponseEntity<List<MonthlyRewardSummaryDTO>> getCustomerMonthlyRewardsSummary(
			@PathVariable Long customerId) {
		List<MonthlyRewardSummaryDTO> rewardsSummary = rewardService.getMonthlyRewardSummary(customerId);
		return ResponseEntity.ok(rewardsSummary);
	}

}
