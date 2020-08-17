<%@page import="model.Account"%>
<%@page import="model.Transaction"%>
<%@page import="dao.TransactionDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    

<%@ page import="java.io.*,java.util.*,java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Details</title>
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
		// get account from session
		Account account = (Account) session.getAttribute("account");
		int accountNo = account.getAccountNo();
		
		// get list of transactions
		TransactionDao d = new TransactionDao();
		List<Transaction> list = d.getAllTransactions(accountNo);
	
		request.setAttribute("list", list);
		request.setAttribute("accountNo", accountNo);
		
		// decimal format for dollar amount
		DecimalFormat df = new DecimalFormat("#.##");
		df.setMinimumFractionDigits(2);
		request.setAttribute("df", df);
	} catch (Exception e){}
	%>
	
	<!-- Prints data in a table -->
	<h3>Transactions for account: ${accountNo}</h3>
	<table class="table">
  		<thead>
   			 <tr>
			      <th scope="col">Type</th>
			      <th scope="col">Amount</th>
			      <th scope="col">Current Balance</th>
			      <th scope="col">Date</th>
   			</tr>
 		</thead>
  		<tbody>
 
			<c:forEach items="${list}" var="t">
				<tr>
					<td><c:out value="${t.getTransType()}" /></td>
					<td><c:out value="$ ${df.format(t.getAmount())}" /></td>
					<td><c:out value="$ ${df.format(t.getCurrentBalance())}" /></td>
					<td><c:out value="${t.getDate().toString()}" /></td>
				</tr>
			</c:forEach>
	  	</tbody>
	</table>
	
	<a href="success.jsp"> Back to home page</a>
	<br><br>
	
	<form method="post" action="LogoutServlet">
		<button type="submit"  class="btn btn-outline-primary">Logout</button>
	</form>
</body>
</html>