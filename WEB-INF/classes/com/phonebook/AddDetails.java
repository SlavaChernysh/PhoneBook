package com.phonebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddDetails
 */
@WebServlet("/AddDetails")
public class AddDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddDetails() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String in1 = request.getParameter("firstname");
		String in2 = request.getParameter("lastname");
		String in3 = request.getParameter("email");
		String in4 = request.getParameter("phone");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\n" +
			       "<html>\n" +
			       "<head><title>Your details.</title></head>\n" +
			       "<body bgcolor=\"#c0c0c0\">\n"+
			       "<h1>Result:</h1>\n"+
			       "<p><ins>You entered the information:</ins></p>\n" + 
			       "<p> FirstName: "+ in1 + "</p>\n" +
			       "<p> LastName: "+ in2 + "</p>\n" +
			       "<p> Email: "+ in3 + "</p>\n" +
			       "<p> Phone: "+ in4 + "</p>\n" +
			       "<p><b> and it has just been stored.</b></p>\n"+
			       "<p>"+ new Date().toString( )+ "</p>\n");	
    		   		   
		try {
			Class.forName("com.mysql.jdbc.Driver");
			out.write("<p>"+"Loaded driver"+ "</p>\n");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebookdb","root","root");
			out.write("<p>"+"Connected to MySQL"+ "</p>\n");
			out.write("<p><ins>Your current phonebook database:</ins></p>\n");
	
			String query1  = "SELECT  *  from	phone_book";
		    stmt  =	conn.createStatement();
		    String insert1 = "INSERT INTO phone_book VALUES  ('" + in1 + "', '" + in2 + "', '" + in3 + "', '" + in4 + "')";
			stmt.executeUpdate(insert1);	    
		    rs  =	stmt.executeQuery(query1);	
		    while (rs.next()){
			   String fn = rs.getString("FIRSTNAME");
			   String ln = rs.getString("LASTNAME");
			   String em = rs.getString("EMAIL");
			   String ph = rs.getString("PHONE");
		   out.write("<p>"+fn+" , "+ln+" , "+em+" , "+ph+ "</p>\n");
		   }
		   
		} catch (SQLException ex) {
		    out.write("<p> Failed: " + ex.getMessage()+ " , "+ ex.getErrorCode()+ "</p>\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
		try
		{rs.close();
		stmt.close();
		conn.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		}
		
		out.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
