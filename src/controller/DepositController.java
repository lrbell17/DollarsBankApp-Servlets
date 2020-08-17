package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import handler.TransactionHandler;
import model.Account;

@WebServlet("/DepositServlet")
public class DepositController extends HttpServlet {

	private static final long serialVersionUID = 5039746702490880316L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		TransactionHandler transactionHandler = new TransactionHandler();

		// getting account from session
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		

		double amount = Double.parseDouble(request.getParameter("amount"));
		boolean success = transactionHandler.handleDeposit(account, amount);

		if (success) {
			out.println("<p style=color:blue>Deposit sucessful!</p>");
			
			account.setBalance(account.getBalance() + amount);
			session.setAttribute("account", account);
			request.getRequestDispatcher("success.jsp").include(request, response);
		} else {
			out.println("<p style=color:red>Unable to process desposit!</p>");
			request.getRequestDispatcher("deposit.jsp").include(request, response);
		}

	}
}
