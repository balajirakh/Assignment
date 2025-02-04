package com.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.RewardPoints;
import com.assignment.model.Transaction;
import com.assignment.repository.RewardRepository;
import com.assignment.repository.TransactionRepository;
import com.assignment.service.RewardServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {
@Autowired
	  private  RewardServiceImpl rewardService;

	    public RewardController(RewardServiceImpl rewardService) {
	        this.rewardService = rewardService;
	    }


	    
	    @GetMapping("/{customerId}")
	    public List<RewardPoints> getCustomerRewards(@PathVariable Long customerId){
	    	List<RewardPoints> rewards = rewardService.getCustomerRewards(customerId);
	    	
	    	if(rewards.isEmpty()) {
	    		
	    		throw new ResourceNotFoundException("no rewards points found for customer Id "+ customerId);
	    	}
	    	return  rewards;
	    }


	

}
