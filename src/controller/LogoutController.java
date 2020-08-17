package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = -3644439154157098982L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// getting account from session
		HttpSession session = request.getSession();
		
		// invalidate session
		session.invalidate();
		
		out.println("<p style=color:blue>Logout sucessful!</p>");
		request.getRequestDispatcher("login.jsp").include(request, response);

	}
}
