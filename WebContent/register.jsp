<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Register</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<style>
	
		body {
			margin: 50px 50px;
		}
	</style>
</head>
<body>
	<h4> Register:</h4>	
	<hr>
	<form method ="post" action="register.do">

		<div class="form-group">
			<label for="exampleInputEmail1">Username</label> <input
					type="text" class="form-control" 
					name="uname" placeholder="Username" required>
		</div>
		<br>
		
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" name="pass"
					placeholder="Password" required>
		</div>
		<br/>
		
		<div class="form-group">
			<label for="exampleInputAcctNo1">Account Number (4 Digits)</label> <input
					type="number" class="form-control" name="accountNo"
					placeholder="Account Number" min="1000" max="9999" step="1" required>
		</div>
		<br/>
		
		<div class="form-group">
			<label for="exampleInputCountry1">Initial Deposit</label> <input
					type="number" class="form-control" name="initialDeposit"
					placeholder="Initial Deposit" min="0.00" step="0.01" required>
		</div>
		<br/>
		
		<button type="submit" class="btn btn-outline-primary">Submit</button>
	</form>
	
	<br><br>
	Already have an account? <a href="login.jsp">Click here to login</a>
	
	
</body>
</html>