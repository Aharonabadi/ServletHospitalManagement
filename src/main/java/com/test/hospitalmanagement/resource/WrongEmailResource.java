package com.test.hospitalmanagement.resource;

import org.owasp.encoder.Encode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WrongEmail")
public class WrongEmailResource extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        unsafe(request, response);

        safe(request, response);

    }

    private void unsafe(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<br><br>Unsafe HTML context:<br>");
            out.write("<h1>" + name + "</h1>");
            out.printf("<h1>" + email + "</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void safe(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<br><br>Unsafe HTML context:<br>");
            out.write("<h1>" + Encode.forHtml(name) + "</h1>");
            out.printf("<h1>" + Encode.forHtml(email) + "</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
