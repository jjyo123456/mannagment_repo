package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Hopsital_details_repo extends JpaRepository<Hospital_data_class , Long> {

    @Query("SELECT h FROM hospital_data h JOIN h.ambulance a WHERE a.id = :id")
    Hospital_data_class findbyambualnceid(@Param("id") String ambulanceid);

    @Query("SELECT h FROM hospital_data h JOIN FETCH h.ambulance a WHERE a.number_plate = :number_plate")
    Hospital_data_class findbyamulancenumber_plate(@Param("number_plate") String number_plate);

    @Query("SELECT h FROM hospital_data h JOIN FETCH h.ambulance a WHERE a.driver_name = :driver_name")
    Hospital_data_class findbyamulancedriver_name(@Param("driver_name") String number_plate);
}
