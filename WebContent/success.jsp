<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Account"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome</title>
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
		Account account = (Account) session.getAttribute("account");
	
		request.setAttribute("uname", account.getUname());
		request.setAttribute("balance", account.getBalance());
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setMinimumFractionDigits(2);
		request.setAttribute("df", df);
	} catch (Exception e){}
	%>
	
	<h3> Welcome ${uname}, your balance is $${df.format(balance)} </h3>
	<hr>
	<a href="transactions.jsp">View all Transactions</a> <br>
	<a href="deposit.jsp">Deposit</a><br>
	<a href="withdraw.jsp">Withdraw</a><br>
	<a href="transfer.jsp">Transfer</a><br>
	<a href="accountInfo.jsp">Account Information</a>
	<br><br>
		
	<form method="post" action="LogoutServlet">
		<button type="submit"  class="btn btn-outline-primary">Logout</button>
	</form>

</body>
</html>