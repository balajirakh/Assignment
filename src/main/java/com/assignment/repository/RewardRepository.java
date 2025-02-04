package com.assignment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assignment.model.RewardPoints;

@Repository
public interface RewardRepository extends JpaRepository<RewardPoints, Long> {

	List<RewardPoints> findByCustomerId(Long customerId);
}
