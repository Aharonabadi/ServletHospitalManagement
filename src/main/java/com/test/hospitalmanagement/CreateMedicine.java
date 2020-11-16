package com.test.hospitalmanagement;

import com.test.hospitalmanagement.database.JDBCConfiguration;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateMedicine
 */
@WebServlet("/CreateMedicine")
public class CreateMedicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMedicine() {
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

		PrintWriter out = response.getWriter();

		String name = org.owasp.esapi.ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.ANSI_MODE), request.getParameter("name"));
		String price = request.getParameter("price");
		String count = request.getParameter("count");

		int success = saveMedicine(response, name, price, count);

		if (success == 1) {
			response.setContentType("text/html");
			out.println("<br><br><br><h1 align=center><font color=\"green\">SUCCESSFUL<br></font></h1><script type=\"text/javascript\">");
			//out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
			out.println("</script>");
		} else {
			response.setContentType("text/html");
			out.println("<br><br><br><h1 align=center><font color=\"red\">THERE IS PROBLEM<br></font></h1><script type=\"text/javascript\">");
			//out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
			out.println("</script>");
		}
	}

	private int saveMedicine(HttpServletResponse response, String name, String price, String count) throws IOException {

		PrintWriter out = response.getWriter();
		int success = 0;

		try {
			String sql = "insert into medicine(name,price,count) values(?,?,?)";

			PreparedStatement ps = JDBCConfiguration.getJDBCConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, price);
			ps.setString(3, count);
			ps.addBatch();

			success += ps.executeBatch()[0];
			ps.clearBatch();

		} catch (SQLException e) {
			response.setContentType("text/html");
			out.println("<br><br><br><h1 align=center><font color=\"red\">THERE IS PROBLEM<br></font></h1><script type=\"text/javascript\">");
			//out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
			out.println("</script>");
			e.printStackTrace();
		}

		return success;
	}

}
