package com.test.hospitalmanagement.resource;

import org.owasp.encoder.Encode;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SanitizerAttributeContext")
public class SanitizerAttributeContextResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String buttonString = "<button value=\"" + name + "\">Hover me</button>";
        System.out.println(buttonString);

        out.println("<br><br>Not sanitized:<br>");
        out.println(buttonString);

        out.println("<br><br>Wrong1 (this happens when encoding too much):<br>");
        out.println(Encode.forHtml(buttonString));

        out.println("<br><br>Bad (Encode.forHtml in attribute context):<br>");
        out.println("<button value=\"" + Encode.forHtml(name) + "\">Hover me</button>");

        out.println("<br><br>Good:<br>");
        out.println("<button value=\"" + Encode.forHtmlAttribute(name) + "\">Hover me</button>");

        out.println("<br><br>Bad (Encode.forHtmlAttribute in unquoted attribute context):<br>");
        out.println("<button value=" + Encode.forHtmlAttribute(name) + ">Hover me</button>");

        out.println("<br><br>Good:<br>");
        out.println("<button value=" + Encode.forHtmlUnquotedAttribute(name) + ">Hover me</button>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
