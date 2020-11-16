package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatepatientemailbyname")
public class UpdatePatientEmailByNameResource extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientService patientService = new PatientServiceImpl();
        patientService.updatePatientEmailByName(name, email);

        Optional<PatientDto> updatedPatientDto = patientService.getPatient(name);

        updatedPatientDto.ifPresent(thePatient -> {

            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.write(thePatient.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
