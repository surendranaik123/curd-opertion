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

public class Deleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		request.setAttribute("name", name);

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("connection done");
			PreparedStatement pst = con.prepareStatement("delete from Registration  where name=?");

			pst.setString(1, name);
			pst.executeUpdate();

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				out.print("Delete Failure");

			} else {

				out.print("Delete sucessfully");

			}
			rs.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
