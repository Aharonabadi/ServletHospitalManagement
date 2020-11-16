package com.test.hospitalmanagement.resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

@WebServlet("/PrintUserInputWithJspWriterPrintUnsafe")
public class PrintUserInputWithJspWriterPrintUnsafeResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageContext pageContext = null;
        JspWriter out = null;
        JspFactory jspFactory = JspFactory.getDefaultFactory();

        response.setContentType("text/html");
        pageContext = jspFactory.getPageContext(this, request, response,null, true, 8192, true);
        out = pageContext.getOut();

        out.write("<html>\n");
        out.write("<head>\n");
        out.write("<meta charset=\"utf-8\">\n");
        out.write("<title>JspWriter</title>\n");
        out.write("</head>\n");
        out.write("<body>\n");
        out.write(request.getParameter("name"));
        out.write("\n");
        out.write("</body>\n");
        out.write("</html>\n");
        out.flush();

    }
}
