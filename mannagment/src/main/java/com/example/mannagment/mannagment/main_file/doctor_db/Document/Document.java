package com.example.mannagment.mannagment.main_file.doctor_db.Document;

import com.example.mannagment.mannagment.auth_file.user_object;
import com.example.mannagment.mannagment.main_file.doctor_db.Document_category.DocumentCategory;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Who uploaded the document (user_object)
    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private user_object uploadedBy;

    // Type of document
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    // File storage URL
    @Column(nullable = false)
    private String fileUrl;

    // Category/folder for grouping
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentCategory.CategoryName categoryFolder;

    // Analyzed / extracted data in JSON or text format
    @Lob
    private String analyzedData;

    // Status: ACTIVE / DELETED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;

    public Document() {
        this.uploadedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Enums
    public enum DocumentType {
        Prescription,
        LabReport,
        XRay,
        Scan,
        Bill,
        Other
    }

    public enum Status {
        ACTIVE,
        DELETED
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public user_object getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(user_object uploadedBy) { this.uploadedBy = uploadedBy; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public DocumentCategory.CategoryName getCategoryFolder() { return categoryFolder; }
    public void setCategoryFolder(DocumentCategory.CategoryName categoryFolder) { this.categoryFolder = categoryFolder; }

    public String getAnalyzedData() { return analyzedData; }
    public void setAnalyzedData(String analyzedData) { this.analyzedData = analyzedData; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
