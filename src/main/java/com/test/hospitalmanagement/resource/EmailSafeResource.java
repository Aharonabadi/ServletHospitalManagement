package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.util.Util;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/emailsafe")
public class EmailSafeResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if(Util.isValidEmailAddress(email)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Valid email " + email);
        } else {
            response.setContentType("text/html");
            response.sendError(HttpStatus.SC_BAD_REQUEST, "Invalid Email");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
