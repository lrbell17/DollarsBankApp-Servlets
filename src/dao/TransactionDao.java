package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionDao {

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
	
	public boolean addTransaction(int accountNo, Transaction transaction) {
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into transactions values(?,?,?,?,?)");
			stmt.setInt(1, accountNo);
			stmt.setString(2, transaction.getTransType());
			stmt.setDouble(3, transaction.getAmount());
			stmt.setDouble(4, transaction.getCurrentBalance());
			stmt.setString(5, transaction.getDate());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("Unable to add account");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Transaction> getAllTransactions(int accountNo) {

		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			Connection con=getConnection();  
			
			PreparedStatement ps=con.prepareStatement("select * from transactions where accountNo = ?");  
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();
			
			while ((rs.next())) {
				Transaction t = new Transaction();
				t.setTransType(rs.getString(2));
				t.setAmount(rs.getDouble(3));
				t.setCurrentBalance(rs.getDouble(4));
				t.setDate(rs.getString(5));
			
				transactions.add(t);
			}
			
		} catch (Exception e) {
			System.out.println("Unable to get user");
			e.printStackTrace();
		}	
		

		return transactions;	
	}
}
