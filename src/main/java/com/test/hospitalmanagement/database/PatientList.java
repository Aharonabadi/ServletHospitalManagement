package com.test.hospitalmanagement.database;

import com.test.hospitalmanagement.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientList {

    private static List<Patient> patients = null;

    public static List<Patient> getPatients(){
        if(patients == null) {
            patients = new ArrayList<>();
            patients.add(new Patient("james", "james@mail.com"));
            patients.add(new Patient("john", "john@mail.com"));
        }

        return patients;
    }

    public static String getPatientsToString(){
        StringBuilder builder = new StringBuilder();

        if(patients != null) {
            for(Patient patient: patients){
                builder.append(patient.toString());
            }
        }

        return builder.toString();
    }

    public static String getPatientEmails(){
        StringBuilder builder = new StringBuilder();

        if(patients != null) {
            for(Patient patient: patients){
                builder.append(patient.getEmail());
            }
        }

        return builder.toString();
    }
}
