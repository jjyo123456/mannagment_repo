package com.example.mannagment.mannagment.sos_file.ambulance_details;

import jakarta.persistence.*;
import org.hibernate.id.IncrementGenerator;

import java.sql.Timestamp;

public class ambulance_data_class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int hospital_id;
    private String number_plate;
    private Enum<ambulance_enum> status;
    private String driver_name;
    private int driver_contact;
    private Double latitude;
    private Double longitude;

    @ManyToOne( , CascadeType.ALL , FetchType.LAZY , true)
    private Timestamp last_location_updated_time;
}
