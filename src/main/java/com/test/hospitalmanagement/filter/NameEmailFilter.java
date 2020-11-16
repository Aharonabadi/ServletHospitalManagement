package com.test.hospitalmanagement.filter;

import com.test.hospitalmanagement.database.PatientList;
import com.test.hospitalmanagement.entity.Patient;
import com.test.hospitalmanagement.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filterpatientnameemail"})
public class NameEmailFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientList.getPatients().add(new Patient(Util.sanitizeUntrustedHTML(name), Util.sanitizeUntrustedHTML(email)));

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}