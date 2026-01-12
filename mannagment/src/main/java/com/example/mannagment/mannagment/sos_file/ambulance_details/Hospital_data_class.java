package com.example.mannagment.mannagment.sos_file.ambulance_details;


import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info;
import jakarta.persistence.*;
import org.hibernate.engine.spi.CascadeStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital_data")
public class Hospital_data_class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_name", length = 50, nullable = false)
    private String hospitalName;

    @Column(name = "address", length = 50)
    private String address;

    @OneToMany(mappedBy = "Ambulance_data_class" , cascade = CascadeType.ALL , orphanRemoval = true)
    List<Ambulance_data_class> ambulance = new ArrayList<>();

    @Column(name = "latitude", precision = 10, scale = 6)
    private Double latitude;

    @Column(name = "longitude", precision = 10, scale = 6)
    private Double longitude;

    @Column(name = "contact_number")
    private String contactNumber;  // Better keep as String to handle +91, leading zeros, etc.

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "Doctor_info" , cascade = {CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REMOVE , CascadeType.REFRESH} , orphanRemoval = true)
    @JoinColumn
    private ArrayList<Doctor_info> hospital_doctor_infos;


    public Hospital_data_class() {}

    public Hospital_data_class(String hospitalName, String address, Double latitude, Double longitude, String contactNumber) {
        this.hospitalName = hospitalName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactNumber = contactNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

