package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.LoginBean;
import model.Transaction;

public class AccountDao {
	
	public static final String uname = "root";
	public static final String password = "root";
	
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String dburl = "jdbc:mysql://localhost/test";
	
	// Establishing Connection
	public static Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
			System.out.println("Unable to get forName()");
			e.printStackTrace();
		}
			
		try {
				conn= DriverManager.getConnection(dburl, uname, password);
				
			} catch (SQLException e) {
			System.out.println("Unable to get connection");
			e.printStackTrace();
			}
		return conn;
		
	}
	
	public List<Account> getAllUsersDB() {

		List<Account> accountList = new ArrayList<Account>();
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from accounts");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				List<Transaction> transactions = new ArrayList<Transaction>(); // PLACEHOLDER!!!
				
				Account account = new Account(rs.getString(1), rs.getString(2), rs.getDouble(3), 
						rs.getInt(4), transactions);
						accountList.add(account);
						
			}
			
		} catch (Exception e) {
			System.out.println("Unable to retrieve Users from DB");
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	// Get list of all users (only Uname and Password) from DB
	//-------> not really necessary, could combine with userMatch
	public List<LoginBean> getAllUsers() {

		List<LoginBean> userList = new ArrayList<LoginBean>();
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from accounts");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				LoginBean user = new LoginBean(rs.getString(1), rs.getString(2));
						userList.add(user);
						
			}
			
		} catch (Exception e) {
			System.out.println("Unable to retrieve Users from DB");
			e.printStackTrace();
		}
		
		
		return userList;
	}
	
	// Checks if user input matches w/ entry in DB 
	public boolean userMatch(String uname, String pass) {

		List<LoginBean> userList = getAllUsers();

		for (LoginBean user : userList) {
			
			if (user.getUname().equals(uname) && user.getPass().equals(pass)) {
				
				return true;
				
			}
		}
		
		return false;
	}
	
	public boolean userAdd(String uname, String pass, double balance, int accountNo, List<Transaction> transactions) {
		
		TransactionDao transactionDao = new TransactionDao();
		
		if (uname.isEmpty() || pass.isEmpty()) {
			return false;
		}
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into accounts values(?,?,?,?)");
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			stmt.setDouble(3, balance);
			stmt.setInt(4, accountNo);
			stmt.executeUpdate();
			
			// add transactions to separate table
			for (Transaction t: transactions) {
				transactionDao.addTransaction(accountNo, t);
			}
			
			
			return true;
		} catch (Exception e) {
			System.out.println("Unable to add account");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Account getAccountByAccountNo(int accountNo) {
		
		Account account = new Account();
		
		try {
			Connection con=getConnection();  
			PreparedStatement ps=con.prepareStatement("select * from accounts where accountNo =?");  
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				List<Transaction> transactions = new ArrayList<Transaction>();
				account.setUname(rs.getString(1));
				account.setPass(rs.getString(2));
				account.setBalance(rs.getDouble(3));
				account.setAccountNo(rs.getInt(4));
				account.setTransactions(transactions);
			}
			
		} catch (Exception e) {
			System.out.println("Unable to get user");
			e.printStackTrace();
		}	
		return account;	
	}
	
	public Account getAccountByUName(String uname) {
		
		Account account = new Account();
		
		try {
			Connection con=getConnection();  
			PreparedStatement ps=con.prepareStatement("select * from accounts where uname =?");  
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				List<Transaction> transactions = new ArrayList<Transaction>();
				account.setUname(rs.getString(1));
				account.setPass(rs.getString(2));
				account.setBalance(rs.getDouble(3));
				account.setAccountNo(rs.getInt(4));
				account.setTransactions(transactions);
			}
			
		} catch (Exception e) {
			System.out.println("Unable to get user");
			e.printStackTrace();
		}	
		return account;	
	}
	
	public boolean updateBalance(Account account, double balance) {
		
		
		String uname = account.getUname();
		String pass = account.getPass();
		//double balance = account.getBalance();
		int accountNo = account.getAccountNo();
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("update accounts "
					+ "set uname = '" + uname + "', pass = '" + pass + "', balance = '" + balance
					+ "', accountNo = '" + accountNo
					+ "' where accountNo=?");
			stmt.setInt(1, account.getAccountNo());
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("Unable to update Account");
			e.printStackTrace();
			
		}
		return false;
	}
	
}





