package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.config.TemplateEngineUtil;

import org.owasp.encoder.Encode;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PassUserInputToThymeleaf")
public class PassUserInputToThymeleafResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstname = request.getParameter("firstname");
        String middlename = request.getParameter("middlename");
        String lastname = request.getParameter("lastname");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("firstname", firstname);
        context.setVariable("middlename", Encode.forHtml(middlename));
        context.setVariable("lastname", HomeMadeEncoder(lastname));
        engine.process("PassUserInputToThymeleaf.html", context, response.getWriter());
    }

    private String HomeMadeEncoder(String lastname) {

        String result = lastname;

        result = result.replaceAll("&", "&amp;");
        result = result.replaceAll("<", "&lt;");
        result = result.replaceAll(">", "&gt;");
        result = result.replaceAll("\"", "&#34;");
        //result = result.replaceAll("\"", "&quot;");

        return result;
    }
}