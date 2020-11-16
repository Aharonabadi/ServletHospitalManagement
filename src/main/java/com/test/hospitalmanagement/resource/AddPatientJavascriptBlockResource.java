package com.test.hospitalmanagement.resource;

import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.service.serviceImpl.PatientServiceImpl;
import org.owasp.encoder.Encode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/AddPatientJavascriptBlock")
public class AddPatientJavascriptBlockResource extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        unsafe(request, response);

        safe(request, response);
    }

    private void unsafe(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientDto patientDto = new PatientDto(name, email);

        PatientService patientService = new PatientServiceImpl();

        patientService.savePatient(patientDto);

        Optional<PatientDto> savedPatientDto = patientService.getPatient(name);

        savedPatientDto.ifPresent(thePatient -> {
            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<br><br><h1 align=center><font color=\"red\">Unsafe Javascript Block context:<br></font></h1>");
                out.println("<script type=\"text/javascript\">");
                out.println("function changeText() { document.getElementById('foo').innerHTML = \"" + thePatient.getName() + "\"; }");
                out.println("</script>");
                out.println("<div id=\"foo\">old text</div>");
                out.println("<a onclick=\"changeText();\">");
                out.println("Click me</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void safe(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        PatientDto patientDto = new PatientDto(name, email);

        PatientService patientService = new PatientServiceImpl();

        patientService.savePatient(patientDto);

        Optional<PatientDto> savedPatientDto = patientService.getPatient(name);

        savedPatientDto.ifPresent(thePatient -> {
            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<br><br><h1 align=center><font color=\"green\">Safe Javascript Block context:<br></font></h1>");
                out.println("<script type=\"text/javascript\">");
                out.println("function changeText1() { document.getElementById('foo1').innerHTML = \"" + Encode.forJavaScriptBlock(thePatient.getName()) + "\"; }");
                out.println("</script>");
                out.println("<div id=\"foo1\">old text</div>");
                out.println("<a onclick=\"changeText1();\">");
                out.println("Click me</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
