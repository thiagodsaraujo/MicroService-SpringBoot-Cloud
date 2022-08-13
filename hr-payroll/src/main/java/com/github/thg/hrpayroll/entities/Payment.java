package com.github.thg.hrpayroll.entities;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double dailyIncome;
	private Integer days;
	
	public Payment() {
		
	}

	public Payment(String name, Double dailyIncome, Integer days) {
		super();
		this.name = name;
		this.dailyIncome = dailyIncome;
		this.days = days;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public double getTotal() {
		return days * dailyIncome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dailyIncome, days, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(dailyIncome, other.dailyIncome) && Objects.equals(days, other.days)
				&& Objects.equals(name, other.name);
	}
	
	
	

}
