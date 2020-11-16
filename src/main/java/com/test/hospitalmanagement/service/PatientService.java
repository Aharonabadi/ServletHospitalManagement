package com.test.hospitalmanagement.service;

import com.test.hospitalmanagement.dto.PatientDto;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    Optional<PatientDto> getPatient(String name);

    List<PatientDto> getAllPatients();

    String getAllPatientsToString();

    String getAllPatientEmails();

    void savePatient(PatientDto patient);

    Optional<PatientDto> updatePatientEmailByName(String name, String email);

    void saveAllPatient(List<PatientDto> patients);

    void saveAllPatientWithValidateEmail(List<PatientDto> patients);

    PatientDto getPatientByEmail(String email) throws ServletException;

    boolean addPatientIfValidEmail(PatientDto patientDto);

}