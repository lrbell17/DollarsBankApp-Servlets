package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import model.Account;
import model.LoginBean;
import handler.LoginHandler;
import handler.RegistrationHandler;

public class FronController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		
		AccountDao dao = new AccountDao();
		
		PrintWriter out = response.getWriter();
		
		// Determine which page the request is coming from:
		String url=request.getRequestURL().toString(); // returns absolute path
		int lastSlash = url.lastIndexOf("/");
		int lastUri = url.lastIndexOf(".do");
		
		String actualPath = (String) url.subSequence(lastSlash + 1, lastUri); // slice to return something more readable

		
		// LOGIN : if request comes from login.jsp --------------------------------------------------------
		if (actualPath.equals("login")) {

			// LoginBean object --> contains getters/setters
			LoginBean lb = new LoginBean(request.getParameter("uname"), request.getParameter("pass"));

			// Checks if login was successful
			LoginHandler handler = new LoginHandler();
			String result = handler.validate(lb);

			if (result.equals("success")) {
				
				// get account from DB
				String uname = request.getParameter("uname");
				Account acct = dao.getAccountByUName(uname);
				
				// add account to session
				HttpSession session = request.getSession();
				session.setAttribute("account", acct);
				
				response.sendRedirect("success.jsp");
			} else {
				out.println("<p style=color:red>Invalid username/password, please try again!</p>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
		}
		
		// REGISTRATION: if request comes from register.jsp -------------------------------------------------
		if (actualPath.equals("register")) {
			
			// Create Account
			Account rb = new Account(request.getParameter("uname"), request.getParameter("pass"), 
					Integer.parseInt(request.getParameter("accountNo")), 
					Double.parseDouble(request.getParameter("initialDeposit")));
			
			// Checks if registration was sucessful
			RegistrationHandler handler = new RegistrationHandler();
			String result = handler.validate(rb);
			
			// Redirects to corresponding page
			if (result.equals("success")) {
				out.println("<p style=color:blue>Registration successful, please login!</p>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			else if (result.equals("existing user")) {
				out.println("<p style=color:red>This username already exists in our records, please try again!</p>");
				request.getRequestDispatcher("register.jsp").include(request, response);
			}
			else {
				out.println("<p style=color:red>Registration failed, please try again!</p>");
				request.getRequestDispatcher("register.jsp").include(request, response);
			}
			
		}
	}

}
