package com.test.hospitalmanagement.resource;


import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.test.hospitalmanagement.util.Util;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getpatientbyemail")
public class GetPatientByEmailResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        PatientService patientService = new PatientServiceImpl();
        PatientDto patientDto = patientService.getPatientByEmail(email);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write(patientDto.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
