package com.test.hospitalmanagement.filter;

import com.test.hospitalmanagement.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filterpatientemail"})
public class EmailFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String email = request.getParameter("email");

        if(Util.isValidEmailAddress(email)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new ServletException("wrong email");
        }
    }

    @Override
    public void destroy() {

    }
}