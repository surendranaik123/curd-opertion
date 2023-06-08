package com.suri.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginvalidation extends HttpServlet {
	private static final long serialVersionUID = 1L;


public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        request.setAttribute("email", email);
        request.setAttribute("password", password);
 
        try {
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("connection done");
			PreparedStatement pst = con.prepareStatement("select * from Registration where email=? and password=?");

			pst.setString(1, email);
			pst.setString(2, password);
			
			
			ResultSet rs = pst.executeQuery();
			RequestDispatcher requestDispatcher=null;
			
			if(rs.next()){ 
				 out.print("sucess");
				
				 requestDispatcher = request.getRequestDispatcher("sucess.html");
					requestDispatcher.forward(request, response);
		    }  
		    else{  
		         
		    	out.print("Sorry username or password error"); 
		    	requestDispatcher = request.getRequestDispatcher("failed.html");
				requestDispatcher.forward(request, response);;  
		    }  
		          
		    out.close();  
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
