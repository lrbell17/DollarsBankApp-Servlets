package model;

import java.util.Date;

public class Transaction {
	
	private String transType;
	private double amount;
	private double currentBalance;
	private String date;
	
	// constructor
	public Transaction(String transType, double amount, double currentBalance) {
		super();
		this.transType = transType;
		this.amount = amount;
		this.currentBalance = currentBalance;
		Date d = new Date();
		this.date = d.toString();
	}
	
	// default constructor
	public Transaction() {}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	@Override
	public String toString() {
		return "Transaction [transType=" + transType + ", amount=" + amount + ", currentBalance=" + currentBalance
				+ ", date=" + date + "]";
	}

	
}
