package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment;

import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "doctor_patient_assignment",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_id", "patient_id"})
        }
)
public class Doctor_patient_assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Doctor who gets access
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor_info doctor;

    // Patient whose data is accessible
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // When access was granted
    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    public Doctor_patient_assignment() {
        this.assignedAt = LocalDateTime.now();
    }

    // -------- Getters & Setters --------

    public Long getId() {
        return id;
    }

    public Doctor_info getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor_info doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
}
