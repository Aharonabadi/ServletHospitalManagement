package com.test.hospitalmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateAssistant
 */
@WebServlet("/CreateAssistant")
public class CreateAssistant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAssistant() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("name", request.getParameter("name"));
        parameterMap.put("email", request.getParameter("email"));
        parameterMap.put("phone", request.getParameter("phone"));
        parameterMap.put("pwd", request.getParameter("pwd"));
        parameterMap.put("joindate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		PrintWriter out = response.getWriter();
		Connection c;

		try {
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
		String sql = "insert into assistant(name,email,phone,joindate,password) values('" + parameterMap.get("name") + "',?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);

		ps.setString(1,parameterMap.get("email"));
		ps.setString(2,parameterMap.get("phone"));
		ps.setString(3,parameterMap.get("joindate"));
		ps.setString(4,parameterMap.get("pwd"));
		ps.addBatch();

		// Executing SQL
		int successCount = 0;
		successCount += ps.executeBatch()[0];
		ps.clearBatch();
		
		if(successCount == 1) {
			response.sendRedirect("login.html");
		}
		else {
			response.setContentType("text/html");  
			out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTERATION PAGE</font></h1><script type=\"text/javascript\">");  
			out.println("redirectURL = \"newAssistant.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
			out.println("</script>");
		}
		} catch (SQLException e) { 
			e.printStackTrace();
			response.setContentType("text/html");  
			out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTERATION PAGE</font></h1><script type=\"text/javascript\">");  
			out.println("redirectURL = \"newAssistant.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
			out.println("</script>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
