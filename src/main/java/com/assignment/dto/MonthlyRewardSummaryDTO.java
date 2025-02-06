package com.assignment.dto;

public class MonthlyRewardSummaryDTO {

	private Integer month;
    private Integer year;
    private Long totalPoints;

    public MonthlyRewardSummaryDTO(Integer month, Integer year, Long totalPoints) {
        this.month = month;
        this.year = year;
        this.totalPoints = totalPoints;
    }

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

    
	
}
