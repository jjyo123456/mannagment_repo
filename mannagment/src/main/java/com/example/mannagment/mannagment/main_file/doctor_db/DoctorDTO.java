package com.example.mannagment.mannagment.main_file.doctor_db;

import java.util.List;

public class DoctorDTO {

    private Long id;
    private Long userId;               // Link to user_object
    private String name;
    private String email;
    private String phone;
    private String gender;

    private Long hospitalId;           // Only sending hospital ID
    private String hospitalName;       // Optional, useful for display

    private String specialty;
    private Integer yearsOfExperience;
    private Double consultationFee;
    private String profilePictureUrl;

    // Optional: list of assigned patient IDs (if needed)
    private List<Long> patientIds;

    // Constructors
    public DoctorDTO() {}

    public DoctorDTO(Long id, Long userId, String name, String email, String phone, String gender,
                     Long hospitalId, String hospitalName, String specialty,
                     Integer yearsOfExperience, Double consultationFee, String profilePictureUrl,
                     List<Long> patientIds) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
        this.consultationFee = consultationFee;
        this.profilePictureUrl = profilePictureUrl;
        this.patientIds = patientIds;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Long getHospitalId() { return hospitalId; }
    public void setHospitalId(Long hospitalId) { this.hospitalId = hospitalId; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public List<Long> getPatientIds() { return patientIds; }
    public void setPatientIds(List<Long> patientIds) { this.patientIds = patientIds; }
}
