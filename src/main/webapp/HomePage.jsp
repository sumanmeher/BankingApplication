<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        header {
            background: linear-gradient(to right, #56CCF2, #2F80ED);
            color: #fff;
            text-align: center;
            padding: 40px 0;
            margin-bottom: 20px;
        }

        h1 {
            margin: 0;
            font-weight: 600;
            font-size: 36px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .greeting {
            text-align: center;
            font-size: 20px;
            color: #333;
            margin-bottom: 30px;
        }

        .button-container {
            text-align: center;
        }

        .button-container a {
            display: inline-block;
            font-size: 18px;
            padding: 10px 20px;
            margin: 10px;
            background-color: #2F80ED;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease;
        }

        .button-container a:hover {
            background-color: #1E63B8;
        }

        .footer {
            text-align: center;
            color: #777;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to the Bank</h1>
    </header>
    <div class="container">
        <div class="greeting">
            <%
            session = request.getSession();
            String s1 = (String) session.getAttribute("cust_name");
            out.println("Hi " + s1 + ", <BR> Welcome to your account! <BR><BR>Please select an operation to perform");
            %>
        </div>
        <div class="button-container">
            <a href="CheckBalance">Check Balance</a>
            <br><br>
            <a href="ChangePin.html">Change Pin</a>
        </div>
    </div>
    <div class="footer">
        &copy; 2023 Bank Application. All rights reserved..
    </div>
</body>
</html>