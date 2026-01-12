package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment.dto;

public class DoctorPatientAssignmentCreateDTO {

    private Long doctorId;
    private Long patientId;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}

