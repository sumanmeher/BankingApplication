package com.java.digitTraing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet res1, res2,res3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cust_id= Integer.parseInt(req.getParameter("cust_id"));;
		String s_ifsc=req.getParameter("sender_ifsc");
		int s_accno= Integer.parseInt(req.getParameter("sender_accno"));
		int pin= Integer.parseInt(req.getParameter("pin"));
		String r_ifsc=req.getParameter("receiver_ifsc");
		int r_accno= Integer.parseInt(req.getParameter("receiver_accno"));
		int amount= Integer.parseInt(req.getParameter("amount"));
		String bank_name=req.getParameter("bank_name");
		int t_id=new Random().nextInt(900000)+100000;
		
		
		String url = "jdbc:mysql://localhost:3306/banking application";
		String user = "root";
		String pwd = "admin@12345";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("select * from bankapp where cust_id=? and ifsc_code=? and acc_no=? and pin=?");
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, s_ifsc);
			pstmt.setInt(3, s_accno);
			pstmt.setInt(4, pin);
			
			res1=pstmt.executeQuery();
			if(res1.next()==true) {
				pstmt = con.prepareStatement("select * from bankapp where ifsc_code=? and acc_no=?");
				pstmt.setString(1, r_ifsc);
				pstmt.setInt(2, r_accno);
				
				res2=pstmt.executeQuery();
				if(res2.next()==true) {
					pstmt = con.prepareStatement("select balance from bankapp where acc_no=?");
					pstmt.setInt(1, s_accno);
					
					res3=pstmt.executeQuery();
					res3.next();
					int bal=res3.getInt(1);
					if(bal>amount) {
						pstmt=con.prepareStatement("update bankapp set balance=balance-? where acc_no=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, s_accno);
						
						int x1=pstmt.executeUpdate();
						if(x1>0) {
							pstmt=con.prepareStatement("update bankapp set balance=balance+? where acc_no=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, r_accno);
							
							int x2=pstmt.executeUpdate();
							if(x2>0) {
								pstmt=con.prepareStatement("insert into transfer_status values(?,?,?,?,?,?,?,?) ");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2,bank_name);
								pstmt.setString(3, s_ifsc);
								pstmt.setInt(4, s_accno);
								pstmt.setString(5, r_ifsc);
								pstmt.setInt(6, r_accno);
								pstmt.setInt(7, amount);
								pstmt.setInt(8, t_id);
								
								int x3=pstmt.executeUpdate();
								if(x3>0) {
									//System.out.println("transfer success");
									resp.sendRedirect("/BankingApplication/TransferSuccess.html");
								}else {
									//System.out.println("store transaction detail");
									resp.sendRedirect("/BankingApplication/TransferFail.jsp");
								}
							}else {
								//System.out.println("credit amount to receiver");
								resp.sendRedirect("/BankingApplication/TransferFail.jsp");
							}
						}else {
							//System.out.println("debit amount from sender");
							resp.sendRedirect("/BankingApplication/TransferFail.jsp");
						}
					}else {
						//System.out.println("check sufficient balance");
						resp.sendRedirect("/BankingApplication/TransferFail.jsp");
					}
				}else {
					//System.out.println("receiver ifsc and accno");
					resp.sendRedirect("/BankingApplication/TransferFail.jsp");
				}
			}else {
				//System.out.println("sender cust_id,ifsc");
				resp.sendRedirect("/BankingApplication/TransferFail.jsp");
			}
		}catch (Exception e) {
		}
	}
}
