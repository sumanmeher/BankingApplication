<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f0f0f0;
	margin:40px 0;
	padding: 0;
}

h3 {
	text-align: center;
	margin-top: 40px;
	font-size: 24px;
	color: #333;
}

.container {
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	max-width: 400px;
}

.return-button {
	display: block;
	width: 100%;
	text-align: center;
	font-size: 18px;
	padding: 10px 0;
	margin-top: 20px;
	background-color: #2F80ED;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	transition: background-color 0.3s ease;
}

.return-button:hover {
	background-color: #1E63B8;
}

.footer {
	text-align: center;
	color: #777;
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<h3>
			<%
			session = request.getSession();
			out.println("Your balance is <BR> &#8377; " + session.getAttribute("balance"));
			%>
		</h3>
		<a class="return-button" href="HomePage.jsp">Return to Home Page</a>
	</div>
	<div class="footer">
		&copy;
		<%=java.time.LocalDate.now().getYear()%>
		Bank Application. All rights reserved.
	</div>
</body>
</html>