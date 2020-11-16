package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import com.test.hospitalmanagement.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddPatientWrongEmailBasicHTML")
public class AddPatientWrongEmailBasicHTMLResource extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        if(Util.isValidEmailAddress(email)) {
            PatientDto patientDto = new PatientDto(name, email);
            PatientService patientService = new PatientServiceImpl();
            patientService.savePatient(patientDto);

            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.write("<br><br>Safe HTML context:<br>");
                out.write("<h1>" + name + "</h1>");
                out.write("<h1>" + email + "</h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            RequestDispatcher view = request.getRequestDispatcher("WrongEmail");
            view.forward(request, response);
        }
    }
}
