package pe.edu.upc.utils;

import java.io.Serializable;

public class SummaryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoryName;
	private Double amount;
	public void addAmount(double amount)
	{
		this.amount+=amount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}	
}
