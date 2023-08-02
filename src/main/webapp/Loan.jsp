<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply Loan</title>
</head>
<body>
	<form action="Loan">
		<h1>Apply Loan</h1>
		<select name="loan">
			<option value="HomeLoan">Home Loan</option>
			<option value="EducationalLoan">Educational Loan</option>
			<option value="VehicleLoan">Vehicle Loan</option>
			<option value="GoldLoan">Gold Loan</option>
			<option value="PersonalLoan">Personal Loan</option>

		</select> <br> <br> 
		<label>Enter your choice</label> <input type="text" name="choice">
		<input type="submit" value="Submit"> 

		
	</form>

</body>
</html>