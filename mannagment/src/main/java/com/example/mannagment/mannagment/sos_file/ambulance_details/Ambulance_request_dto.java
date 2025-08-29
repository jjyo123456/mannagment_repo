package com.example.mannagment.mannagment.sos_file.ambulance_details;

public class Ambulance_request_dto {
    private Long hospitalId;
    private String numberPlate;
    private String driverName;
    private int driverContact;
    private Double latitude;
    private Double longitude;

    // getters and setters
    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(int driverContact) {
        this.driverContact = driverContact;
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
}
