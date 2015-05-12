package com.phonebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindDetails
 */
@WebServlet("/FindDetails")
public class FindDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn1 = null;
		Statement stmt1 = null;
		ResultSet rs1=null;
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\n" +
			       "<html>\n" +
			       "<head><title>Search result.</title></head>\n" +
			       "<body bgcolor=\"#c0c0c0\">\n");	
		
    		   		   
		try {
			String search1 = request.getParameter("search");
			Class.forName("com.mysql.jdbc.Driver");
			out.write("<p>"+"Loaded driver"+ "</p>\n");
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebookdb?useUnicode=true&characterEncoding=UTF-8","root","root");
			out.write("<p>"+"Connected to MySQL"+ "</p>\n");
			out.write( "<p><ins>You've entered data for search:</ins></p>\n" + 
				       "<p> "+ search1 + "</p>\n"+
				       "<h1>Result:</h1>\n");
	
			String query2  = ("SELECT  *  from	phone_book where FIRSTNAME LIKE '" + search1 + "' OR LASTNAME LIKE '" + search1 + "' OR EMAIL LIKE '" + search1 + "' OR PHONE LIKE '"+ search1+"'");
			
			stmt1  =	conn1.createStatement();
		    
		    rs1  =	stmt1.executeQuery(query2);	
		    
		    while (rs1.next()){
			   String fns = rs1.getString("FIRSTNAME");
			   String lns = rs1.getString("LASTNAME");
			   String ems = rs1.getString("EMAIL");
			   String phs = rs1.getString("PHONE");
		   out.write("<p>"+fns+" , "+lns+" , "+ems+" , "+phs+ "</p>\n");
		   }
		   
		} catch (SQLException ex) {
		    out.write("<p> Failed: " + ex.getMessage()+ " , "+ ex.getErrorCode()+ "</p>\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
		try
		{rs1.close();
		stmt1.close();
		conn1.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		}
		
		out.write("<a href="+"index.html"+">Back</a>\n"+
		"</body></html>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
