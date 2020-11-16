package com.test.hospitalmanagement.filter;

import com.test.hospitalmanagement.database.PatientList;
import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.entity.Patient;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.test.hospitalmanagement.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filteraddpatient"})
public class AddPatientFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientService patientService = new PatientServiceImpl();
        patientService.savePatient(new PatientDto(name, email));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}