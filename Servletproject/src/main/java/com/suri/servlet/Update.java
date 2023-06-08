package com.suri.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");

		PrintWriter out = response.getWriter();

		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			PreparedStatement pst = con.prepareStatement("UPDATE Registration SET name=? WHERE name='naik'");
			pst.setString(1, name);

			pst.executeUpdate();
			ResultSet rs = pst.executeQuery();

			if (true)
				out.println("<b>You are successfully update</b>");
			rs.close();
		} catch (Exception e) {
			out.println("<b> failed</b>");
			out.println("<b>Error:</b>" + e);
		}
	}
}
