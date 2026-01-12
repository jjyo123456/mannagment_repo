package com.example.mannagment.mannagment.sos_file.ambulance_request;

import com.example.mannagment.mannagment.sos_file.ambulance_details.Ambulance_data_class;
import jakarta.persistence.*;

import javax.xml.stream.Location;
import java.util.UUID;

@Entity
public class Request_data_class {
    @Id
    private String request_id;
    private String user_id;
    private String user_name;
    private String user_email;
    private String user_phone;
    private String user_address;
    private Double lat;
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambulance_id")
    private Ambulance_data_class ambulance_assigned;


    @PrePersist
    public void prePersist(){
        if(request_id == null){
            this.request_id = ambulance_assigned.getAmbulance_id() + "." + UUID.randomUUID().toString();
        }
    }




    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Ambulance_data_class getAmbulance_assigned() {
        return ambulance_assigned;
    }

    public void setAmbulance_assigned(Ambulance_data_class ambulance_assigned) {
        this.ambulance_assigned = ambulance_assigned;
    }





}
