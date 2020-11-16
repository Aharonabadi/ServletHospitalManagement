package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/patient")
public class PatientResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        PatientService patientService = new PatientServiceImpl();

        Optional<PatientDto> patientDto = patientService.getPatient(name);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(patientDto.isPresent()) {
            out.println(patientDto.toString());
        }
        else {
            response.sendError(HttpStatus.SC_BAD_REQUEST, name + " not found.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
