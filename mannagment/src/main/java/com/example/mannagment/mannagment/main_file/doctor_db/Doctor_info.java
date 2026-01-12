package com.example.mannagment.mannagment.main_file.doctor_db;


import com.example.mannagment.mannagment.auth_file.user_object;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import com.example.mannagment.mannagment.sos_file.ambulance_details.Hospital_data_class;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor_info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to authentication user_object
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user_object user;

    private String name;
    private String email;
    private String phone;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital_data_class hospital;

    private String specialty;
    private Integer yearsOfExperience;
    private Double consultationFee;
    private String profilePictureUrl;

    // Many-to-Many: Doctor ↔ Patients
    @ManyToMany
    @JoinTable(
            name = "doctor_patient_assignment",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor
    public Doctor_info() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public user_object getUser() { return user; }
    public void setUser(user_object user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Hospital_data_class getHospital() { return hospital; }
    public void setHospital(Hospital_data_class hospital) { this.hospital = hospital; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
