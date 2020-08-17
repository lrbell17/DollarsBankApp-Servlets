package handler;

import dao.AccountDao;
import dao.TransactionDao;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountNumberException;
import model.Account;
import model.Transaction;

public class TransactionHandler {

	private AccountDao accountDao;
	private TransactionDao transactionDao;
	
	public TransactionHandler() {
		this.accountDao = new AccountDao();
		this.transactionDao = new TransactionDao();
	}
	
	public boolean handleDeposit(Account account, double amount) {
		
		double balance = account.getBalance() + amount;
		
		try {
			accountDao.updateBalance(account, balance);
			transactionDao.addTransaction(account.getAccountNo(), new Transaction("Deposit", amount, balance));
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void handleWithdraw(Account account, double amount) throws InsufficientFundsException {

		// check if you have enough funds to complete transaction
		if (account.getBalance() < amount) {
			throw new InsufficientFundsException();
		} else {
			double balance = account.getBalance() - amount;

			accountDao.updateBalance(account, balance);
			transactionDao.addTransaction(account.getAccountNo(), new Transaction("Withdraw", amount, balance));
			
		}

	}
	
	public void handleTransfer(Account account, double amount, int accountNo) throws InvalidAccountNumberException,
		InsufficientFundsException {
		
		// the account you want to transfer $$ into
		Account accountForTransfer = accountDao.getAccountByAccountNo(accountNo);
		
		// check for valid account number
		if (accountForTransfer.getUname() == null) {
			throw new InvalidAccountNumberException();
		}
		// check you have enough funds to complete transaction
		else if (account.getBalance() < amount) {
			throw new InsufficientFundsException();
		} 
		else {
			// remove $$ from your account
			double balance = account.getBalance() - amount;
			accountDao.updateBalance(account, balance);
			
			String transType = "Transfer to account: " + accountForTransfer.getAccountNo();
			transactionDao.addTransaction(account.getAccountNo(), new Transaction(transType, amount, balance));
			
			// add $$ to account you want to transfer to 
			double balance2 = accountForTransfer.getBalance() + amount;
			accountDao.updateBalance(accountForTransfer, balance2);
			
			String transType2 = "Transfer from account: " + accountForTransfer.getAccountNo();
			transactionDao.addTransaction(accountForTransfer.getAccountNo(), new Transaction(transType2, amount, balance2));
			
		}
		
	}

}



