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
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePin")
public class ChangePin extends HttpServlet {
	public Connection con;
	public PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int acc_no = (int) session.getAttribute("acc_no");
		int old_pin = Integer.parseInt(req.getParameter("old_pin"));
		int new_pin = Integer.parseInt(req.getParameter("new_pin"));
		int confirm_pin= Integer.parseInt(req.getParameter("confirm_pin"));
		
		String url = "jdbc:mysql://localhost:3306/banking application";
		String user = "root";
		String pwd = "admin@12345";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			if(new_pin==confirm_pin) {
			pstmt = con.prepareStatement("update bankapp set pin=? where acc_no=? and pin=?");
			pstmt.setInt(1, new_pin);
			pstmt.setInt(2, acc_no);
			pstmt.setInt(3, old_pin);

			int x = pstmt.executeUpdate();
			if (x >0) {
				
				resp.sendRedirect("/BankingApplication/PinChangeSuccess.html");
			} else {
				resp.sendRedirect("/BankingApplication/PinChangeFail.html");
			}
			}else {
				resp.sendRedirect("/BankingApplication/PinChangeFail.html");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
