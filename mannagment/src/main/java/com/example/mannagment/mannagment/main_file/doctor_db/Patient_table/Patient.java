package com.example.mannagment.mannagment.main_file.doctor_db.Patient_table;


import com.example.mannagment.mannagment.auth_file.user_object;
import com.example.mannagment.mannagment.main_file.doctor_db.Document.Document;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to user_object
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user_object user;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;  // Use enum: MALE, FEMALE, OTHER

    private String bloodGroup;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // One patient can have many documents
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Document> documents;

    public Patient() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public user_object getUser() { return user; }
    public void setUser(user_object user) { this.user = user; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public List<Document> getDocuments() { return documents; }
    public void setDocuments(List<Document> documents) { this.documents = documents; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}

