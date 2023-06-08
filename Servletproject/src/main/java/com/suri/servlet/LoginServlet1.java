 package com.suri.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		 String password = request.getParameter("password");
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("connection done");
			PreparedStatement pst = con.prepareStatement("select * from Registration where email=? and password=?");

			pst.setString(1, email);
			pst.setString(2, password);

			out.print("<table width=100% border=2>");
			out.print("<caption>Employee Details:</caption>");

			ResultSet rs = pst.executeQuery();

			/* Printing column names */
			out.print("</br></br>");
			ResultSetMetaData rsmd = rs.getMetaData();
			int total = rsmd.getColumnCount();

			out.print("<tr>");

			for (int i = 1; i <= total; i++) {

				out.print("<th>" + rsmd.getColumnName(i) + "</th>");

			}

			out.print("</tr>");

			while (rs.next()) {
				out.print("</td><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + " </td><td>" + rs.getString(3)
						+ "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>"
						+ rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8)
						+ "</td><td>" + rs.getString(9));

			}
			out.print("</table>");

		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			out.close();
		}

	}

}