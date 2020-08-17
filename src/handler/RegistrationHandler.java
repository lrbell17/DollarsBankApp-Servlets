package handler;

import java.util.List;

import dao.AccountDao;

import model.Account;
import model.Transaction;

public class RegistrationHandler {
	
	public String validate(Account account) {
		
		AccountDao d = new AccountDao();
		
		
		String uname = account.getUname();
		String pass = account.getPass();
		int accountNo = account.getAccountNo();
		double balance = account.getBalance();
		List<Transaction> transactions = account.getTransactions();
		
		if (!existingUser(uname)) {
			
			boolean success = d.userAdd(uname, pass, balance, accountNo, transactions);

			if (success) {

				return "success";
			} else {
				return "error";
			}
		}
		else {
			return "existing user";
		}
	}
	
	public boolean existingUser(String uname) {
		AccountDao d = new AccountDao();
		List<Account> accountList = d.getAllUsersDB();
		
		for (Account account : accountList) {
			if (account.getUname().equals(uname)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
