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

@WebServlet("/Loan")
public class Loan extends HttpServlet {
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet resultset;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/banking application";
		String user = "root";
		String pwd = "admin@12345";
		HttpSession session= req.getSession(true);
		
		int l_id= Integer.parseInt(req.getParameter("choice"));

		// Database connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt= con.prepareStatement("select * from loan where l_id=? ");
			
			pstmt.setInt(1, l_id);
			resultset = pstmt.executeQuery();
			if(resultset.next()==true) {
				session.setAttribute("l_id",resultset.getInt(1));
				session.setAttribute("l_type",resultset.getString(2));
				session.setAttribute("tenure",resultset.getInt(3));
				session.setAttribute("interest",resultset.getDouble(4));
				session.setAttribute("description",resultset.getString(5));
				resp.sendRedirect("/BankingApplication/LoanDetails.jsp");
			}else {
				resp.sendRedirect("/BankingApplication/LoanDetailsFail.html");
			}
			
		} catch (Exception e) {
		}
	}
}
