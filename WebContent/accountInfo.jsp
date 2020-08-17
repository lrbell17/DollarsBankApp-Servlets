<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Account"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Account Information</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<style>
		body {
			margin: 50px 50px;
		}
	</style>
</head>

<body>
	
	<% 
		// check for active session --> forwards to login if necessary
		if (session.getAttribute("account")==null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	%>
	
	<% 
	try{
		// getting account from session
		Account account = (Account) session.getAttribute("account");
		
		// setting account info
		request.setAttribute("uname", account.getUname());
		request.setAttribute("password", account.getPass());
		request.setAttribute("balance", account.getBalance());
		request.setAttribute("accountNo", account.getAccountNo());
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setMinimumFractionDigits(2);
		request.setAttribute("df", df);
	} catch (Exception e){}
	%>
	
	<h3> Account information for: ${accountNo} </h3>
	<hr>
	<p> Username: ${uname} </p>
	<p> Password: ${password} </p>
	<p> Balance: ${balance} </p>
	
	<hr>
	<a href="success.jsp"> Back to home page</a>
	<br><br>
	<form method="post" action="LogoutServlet">
		<button type="submit"  class="btn btn-outline-primary">Logout</button>
	</form>

</body>
</html>