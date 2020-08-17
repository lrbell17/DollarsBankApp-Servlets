package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountNumberException;
import handler.TransactionHandler;
import model.Account;

@WebServlet("/TransferServlet")
public class TransferController extends HttpServlet {

	private static final long serialVersionUID = -4042866694855949085L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TransactionHandler transactionHandler = new TransactionHandler();

		// getting account from session
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		
		// get amount and accountNo from transfer.jsp form
		int accountNo = Integer.parseInt(request.getParameter("accountNo"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		try {
			transactionHandler.handleTransfer(account, amount, accountNo);
			out.println("<p style=color:blue>Withdraw sucessful!</p>");
			
			// update balance & add attribute to session 
			account.setBalance(account.getBalance() - amount);
			session.setAttribute("account", account);
			
			request.getRequestDispatcher("success.jsp").include(request, response);
			
		} catch (InsufficientFundsException e) {
			String message = e.getMessage();
			out.println("<p style=color:red>"+ message +"</p>");
			request.getRequestDispatcher("transfer.jsp").include(request, response);
			
		} catch(InvalidAccountNumberException e) {
			String message = e.getMessage();
			out.println("<p style=color:red>"+ message +"</p>");
			request.getRequestDispatcher("transfer.jsp").include(request, response);
			
		} catch(Exception e) {
			out.println("<p style=color:red>Unable to process transfer!</p>");
			request.getRequestDispatcher("transfer.jsp").include(request, response);
		}

	}
}
