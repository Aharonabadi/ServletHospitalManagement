package com.test.hospitalmanagement.service.serviceImpl;

import com.test.hospitalmanagement.database.PatientList;
import com.test.hospitalmanagement.dto.PatientDto;
import com.test.hospitalmanagement.entity.Patient;
import com.test.hospitalmanagement.mapper.PatientMapper;
import com.test.hospitalmanagement.service.PatientService;
import com.test.hospitalmanagement.util.Util;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService {

    @Override
    public Optional<PatientDto> getPatient(String name) {

        Patient patient = PatientList.getPatients().stream()
                .filter(thePatient -> name.equals((thePatient.getName())))
                .findAny()
                .orElse(null);

        return Optional.ofNullable(PatientMapper.MAPPER.toDto(patient));

    }

    @Override
    public List<PatientDto> getAllPatients() {
        return null;
    }

    @Override
    public String getAllPatientsToString() {
        return PatientList.getPatientsToString();
    }

    @Override
    public String getAllPatientEmails() {
        return PatientList.getPatientEmails();
    }

    @Override
    public void savePatient(PatientDto patientDto) {
        PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(patientDto));
    }

    @Override
    public Optional<PatientDto> updatePatientEmailByName(String name, String email) {
        Patient patient = PatientList.getPatients().stream()
                .filter(thePatient -> name.equals((thePatient.getName())))
                .findAny()
                .orElse(null);

        patient.setEmail(email);

        return Optional.ofNullable(PatientMapper.MAPPER.toDto(patient));
    }

    @Override
    public void saveAllPatient(List<PatientDto> patients) {
        for(PatientDto patientDto: patients){
            PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(patientDto));
        }
    }

    @Override
    public void saveAllPatientWithValidateEmail(List<PatientDto> patients) {
        for(PatientDto patientDto: patients){
            Patient patient = PatientMapper.MAPPER.toEntity(patientDto);
            if(Util.isValidEmailAddress(patient.getEmail())) {
                PatientList.getPatients().add(patient);
            }
        }
    }

    @Override
    public PatientDto getPatientByEmail(String email) throws ServletException {
        Patient patient = PatientList.getPatients().stream()
                .filter(thePatient -> email.equals((thePatient.getEmail())))
                .findAny()
                .orElse(null);

        if(patient == null) {
            throw new ServletException(email + " not found");
        }

        return PatientMapper.MAPPER.toDto(patient);
    }

    @Override
    public boolean addPatientIfValidEmail(PatientDto patientDto) {

        if(Util.isValidEmailAddress(patientDto.getEmail())) {
            String name = Util.sanitizeUntrustedHTML(patientDto.getName());
            String email = patientDto.getEmail();
            PatientList.getPatients().add(PatientMapper.MAPPER.toEntity(new PatientDto(name, email)));
            return true;
        }
        return false;
    }

}
