<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Transfer</title>
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
	<h4> Enter Transfer Details:</h4>	
	<hr>
	<form method ="post" action="TransferServlet">

		<div class="form-group">
			<label for="exampleInputEmail1">Account Number</label> <input
					type="number" class="form-control" 
					name="accountNo" placeholder="Account number" required>
		</div>
		<br>
		
		<div class="form-group">
			<label for="exampleInputEmail1">Amount</label> <input
					type="number" class="form-control" 
					name="amount" placeholder="Amount" min="0.00" step="0.01" required>
		</div>
		<br>
		
		<button type="submit" class="btn btn-outline-primary">Transfer</button>
	</form>
	
	<br><hr>
	<a href="success.jsp"> Back to home page</a>
	<br><br>
	
	<form method="post" action="LogoutServlet">
		<button type="submit"  class="btn btn-outline-primary">Logout</button>
	</form>
	
</body>
</html>