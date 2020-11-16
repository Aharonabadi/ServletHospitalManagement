package com.test.hospitalmanagement.resource;

import org.owasp.encoder.Encode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PassUserInputToJavaJspElSafe")
public class PassUserInputToJavaJspElSafeResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", Encode.forHtml(request.getParameter("name")));
        request.getRequestDispatcher("/PassUserInputToJavaJspElSafe.jsp").forward(request, response);
    }
}