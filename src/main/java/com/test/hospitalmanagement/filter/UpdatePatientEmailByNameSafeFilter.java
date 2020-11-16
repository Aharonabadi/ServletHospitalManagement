package com.test.hospitalmanagement.filter;

import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.test.hospitalmanagement.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filterupdatepatientemailbynamesafe"})
public class UpdatePatientEmailByNameSafeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String name = request.getParameter("name");
        String email = Util.isValidEmailAddress(request.getParameter("email")) ? request.getParameter("email"):"";

        PatientService patientService = new PatientServiceImpl();
        patientService.updatePatientEmailByName(name, email);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}