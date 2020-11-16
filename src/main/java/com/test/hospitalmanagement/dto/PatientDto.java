package com.test.hospitalmanagement.dto;

public class PatientDto {

    private String name;
    private String email;

    public PatientDto() {
    }

    public PatientDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PatientDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
