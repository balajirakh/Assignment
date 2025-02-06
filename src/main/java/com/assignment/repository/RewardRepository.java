package com.assignment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.dto.MonthlyRewardSummaryDTO;
import com.assignment.model.RewardPoints;

@Repository
public interface RewardRepository extends JpaRepository<RewardPoints, Long> {

	List<RewardPoints> findByCustomerId(Long customerId);
	
	@Query("SELECT new com.assignment.dto.MonthlyRewardSummaryDTO(r.month, r.year, SUM(r.points)) " +
		       "FROM RewardPoints r WHERE r.customerId = :customerId " +
		       "GROUP BY r.month, r.year " +
		       "ORDER BY r.year DESC, r.month DESC")
		List<MonthlyRewardSummaryDTO> getMonthlyRewardSummary(@Param("customerId") Long customerId);


}
