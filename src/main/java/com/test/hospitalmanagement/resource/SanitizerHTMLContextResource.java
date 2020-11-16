package com.test.hospitalmanagement.resource;

import org.owasp.encoder.Encode;
import org.owasp.esapi.ESAPI;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SanitizerHTMLContext")
public class SanitizerHTMLContextResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println(name);

        out.println("<br><br>Not sanitized:<br>");
        out.println(name);

        out.println("<br><br>Good:<br>");
        out.println(ESAPI.encoder().encodeForHTML(name));

        out.println("<br><br>Good:<br>");
        out.println(Encode.forHtml(name));

        out.println("<br><br>Good:<br>");
        out.println(ESAPI.encoder().encodeForHTMLAttribute(name));

        out.println("<br><br>Good:<br>");
        out.println(Encode.forHtmlAttribute(name));

        out.println("<br><br>Wrong:<br>");
        out.println(policy.sanitize(name));

        out.println("<br><br>Wrong:<br>");
        out.println(ESAPI.encoder().encodeForJavaScript(name));

        out.println("<br><br>Wrong:<br>");
        out.println(Encode.forJavaScript(name));

        // http://localhost:8080/hospital/SanitizerHTMLContext?name=<script>alert('xss')</script>
        // http://localhost:8080/hospital/SanitizerHTMLContext?name=<script>alert(123)</script>
        // http://localhost:8080/hospital/SanitizerHTMLContext?name=bob

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
