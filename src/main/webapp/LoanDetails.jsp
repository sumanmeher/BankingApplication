<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
<%
session=request.getSession();
out.println("Your loan will be : "+session.getAttribute("l_type")+ "<BR> of tenure "+session.getAttribute("tenure")+"<BR> with interest"+session.getAttribute("interest"));
%>

<a href="HomePage.jsp">Redirect</a>
</body>
</html>