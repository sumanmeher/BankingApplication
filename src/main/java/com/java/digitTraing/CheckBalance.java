package com.java.digitTraing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int acc_no = (int) session.getAttribute("acc_no");

		String url = "jdbc:mysql://localhost:3306/banking application";
		String user = "root";
		String pwd = "admin@12345";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("select balance from bankapp where acc_no=?");
			pstmt.setInt(1, acc_no);

			resultSet = pstmt.executeQuery();
			if (resultSet.next() == true) {
				session.setAttribute("balance", resultSet.getInt("balance"));
				resp.sendRedirect("/BankingApplication/Balance.jsp");
			} else {
				resp.sendRedirect("/BankingApplication/BalanceFail.html");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}