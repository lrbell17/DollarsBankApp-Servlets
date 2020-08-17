package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	// Attributes
	private String uname;
	private String pass;
	
	private double initialDeposit;
	private double balance;
	private int accountNo;
	private List<Transaction> transactions;
	
	
	// Constructor (for initial account creation)
	public Account(String uname, String pass, int accountNo, double initialDeposit) {
		super();
		this.uname = uname;
		this.pass = pass;
		this.balance = initialDeposit;
		this.accountNo = accountNo;
		this.transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction("Initial Deposit", initialDeposit, initialDeposit));
	}
	
	// Constructor (for getting from DB)
	public Account(String uname, String pass, double balance, int accountNo, List<Transaction> transactions) {
		super();
		this.uname = uname;
		this.pass = pass;
		this.balance = balance;
		this.accountNo = accountNo;
		this.transactions = transactions;
	}

	// Default constructor
	public Account() {
		
	}

	// Getters and Setters
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [uname=" + uname + ", pass=" + pass + ", initialDeposit=" + initialDeposit + ", balance="
				+ balance + ", accountNo=" + accountNo + ", transactions=" + transactions + "]";
	}


}
