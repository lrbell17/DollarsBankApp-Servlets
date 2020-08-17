# DollarsBankApp-Servlets

A banking application using Java Servlet / JSP architecture with JDBC for storing data in MySQL  
  
**Features:**
* Registration
* Login/Logout
* Deposit, Withdraw, and Transfer funds
* Transaction History
* Account Info

**MySQL Schema:**
```
create table accounts(uname varchar(30), pass varchar(30),  balance float, accountNo int primary key); 
create table transactions(accountNo int, transType varchar(30), amount float, balance float, currentdate varchar(30));
```

**Execution:** run on server from any of the jsp pages, you will be redirected to the login page
