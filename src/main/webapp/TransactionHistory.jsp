<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction History</title>
</head>
<body>

	<%
	int acc_no = (int) session.getAttribute("acc_no");
	String url = "jdbc:mysql://localhost:3306/banking application";
	String user = "root";
	String pwd = "admin@12345";

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pwd);

		PreparedStatement pstmt = con.prepareStatement(
		"select * from transfer_status where sender_accno = ? or receiver_accno=? order by t_id desc");
		pstmt.setInt(1, acc_no);
		pstmt.setInt(2, acc_no);

		ResultSet resultSet = pstmt.executeQuery();

		while (resultSet.next()) {
			if (resultSet.getInt("sender_accno") == acc_no) {

		pstmt = con.prepareStatement("select * from bankapp where acc_no = ?");
		pstmt.setInt(1, resultSet.getInt("receiver_accno"));
		ResultSet secondPerson = pstmt.executeQuery();
		secondPerson.next();

		out.println("Sent:<br>");
		out.println("Sent To: " + resultSet.getInt("receiver_accno") + "<br>");
		out.println("Receiver Name: " + secondPerson.getString("cust_name") + "<br>");

			} else {
		pstmt = con.prepareStatement("select * from bankapp where acc_no = ?");
		pstmt.setInt(1, resultSet.getInt("sender_accno"));
		ResultSet secondPerson = pstmt.executeQuery();
		secondPerson.next();

		out.println("Received:<br>");
		out.println("Received From: " + resultSet.getInt("receiver_accno") + "<br>");
		out.println("Sender Name: " + secondPerson.getString("cust_name") + "<br>");

			}

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
	<br><br>
<a href="HomePage.jsp">Go To Home</a>

</body>
</html>