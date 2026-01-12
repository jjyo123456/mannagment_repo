package com.example.mannagment.mannagment.sos_file.ambulance_details;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.sql.results.graph.Fetch;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "ambulance_data")
public class Ambulance_data_class {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String ambulance_id;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "Hospital" , nullable = false )
    private Hospital_data_class hospital_id;

    private String number_plate;
    private Enum<ambulance_enum> status;
    private String driver_name;
    private int driver_contact;
    private Double latitude;
    private Double longitude;
    private Timestamp last_location_updated_time;






   // public Ambulance_data_class(String ambulance_id, int hospital_id, String number_plate, Enum<ambulance_enum> status, String driver_name, int driver_contact, Double latitude, Double longitude, Timestamp last_location_updated_time) {
   //     this.ambulance_id = ambulance_id;
   //     this.hospital_id = hospital_id;
   //     this.number_plate = number_plate;
   //     this.status = status;
   //     this.driver_name = driver_name;
   //     this.driver_contact = driver_contact;
   //     this.latitude = latitude;
    //    this.longitude = longitude;
    //    this.last_location_updated_time = last_location_updated_time;
 //   }
//





    public String getAmbulance_id() {
        return ambulance_id;
    }

    public void setAmbulance_id(String ambulance_id) {
        this.ambulance_id = ambulance_id;
    }

    public Hospital_data_class getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Hospital_data_class hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }

    public Enum<ambulance_enum> getStatus() {
        return status;
    }

    public void setStatus(Enum<ambulance_enum> status) {
        this.status = status;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public int getDriver_contact() {
        return driver_contact;
    }

    public void setDriver_contact(int driver_contact) {
        this.driver_contact = driver_contact;
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

    public Timestamp getLast_location_updated_time() {
        return last_location_updated_time;
    }

    public void setLast_location_updated_time(Timestamp last_location_updated_time) {
        this.last_location_updated_time = last_location_updated_time;
    }





    @PrePersist
    public void prePersist(){
        if(ambulance_id == null){
            this.ambulance_id = hospital_id + "." + UUID.randomUUID().toString();
        }
    }


}
