package com.test.hospitalmanagement.filter;

import com.test.hospitalmanagement.database.PatientList;
import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.mapper.PatientMapper;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/filteraddpatients"})
public class AddPatientsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientService patientService = new PatientServiceImpl();


        patientService.savePatient(new PatientDto("mary", "mary@gmail.com"));
        patientService.savePatient(new PatientDto("ted", "ted@gmail.com"));

        patientService.savePatient(new PatientDto(name, email));

        PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(new PatientDto("sarah", "sarah@gmail.com")));
        PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(new PatientDto("bill", "bill@gmail.com")));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}