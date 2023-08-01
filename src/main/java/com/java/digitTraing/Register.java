package com.java.digitTraing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")

public class Register extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bank_id = Integer.parseInt(req.getParameter("bank_id"));
		String bank_name = req.getParameter("bank_name");
		String ifsc_code = req.getParameter("ifsc_code");
		int acc_no = Integer.parseInt(req.getParameter("acc_no"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int cust_id = Integer.parseInt(req.getParameter("cust_id"));
		String cust_name = req.getParameter("cust_name");
		int balance = Integer.parseInt(req.getParameter("balance"));
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone"));

		String url = "jdbc:mysql://localhost:3306/banking application";
		String user = "root";
		String pwd = "admin@12345";

		// Database connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("insert into bankapp values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, bank_id);
			pstmt.setString(2, bank_name);
			pstmt.setString(3, ifsc_code);
			pstmt.setInt(4, acc_no);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, cust_id);
			pstmt.setString(7, cust_name);
			pstmt.setInt(8, balance);
			pstmt.setString(9, email);
			pstmt.setLong(10, phone);

			int x = pstmt.executeUpdate();
			if (x > 0) {
				resp.sendRedirect("/BankingApplication/RegisterSuccess.html");
			} else {
				resp.sendRedirect("/BankingApplication/RegisterFail.html");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}