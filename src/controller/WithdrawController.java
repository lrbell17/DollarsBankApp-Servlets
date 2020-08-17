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
import handler.TransactionHandler;
import model.Account;

@WebServlet("/WithdrawServlet")
public class WithdrawController extends HttpServlet {

	private static final long serialVersionUID = 219898294529614639L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TransactionHandler transactionHandler = new TransactionHandler();

		// getting account from session
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		
		// get amount from withdraw.jsp form
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		try {
			transactionHandler.handleWithdraw(account, amount);
			out.println("<p style=color:blue>Withdraw sucessful!</p>");
			
			account.setBalance(account.getBalance() - amount);
			session.setAttribute("account", account);
			request.getRequestDispatcher("success.jsp").include(request, response);
			
		} catch (InsufficientFundsException e) {
			String message = e.getMessage();
			out.println("<p style=color:red>"+ message +"</p>");
			request.getRequestDispatcher("withdraw.jsp").include(request, response);
			
		} catch(Exception e) {
			out.println("<p style=color:red>Unable to process withdraw!</p>");
			request.getRequestDispatcher("withdraw.jsp").include(request, response);
		}

	}
}
