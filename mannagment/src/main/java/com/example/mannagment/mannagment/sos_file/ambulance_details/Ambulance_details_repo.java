package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ambulance_details_repo extends JpaRepository<Ambulance_data_class , String> {

    List<Ambulance_data_class> findAllBystatus(ambulance_enum enumm);

    Ambulance_data_class findBydriver_name(String name);

    Ambulance_data_class findBynumber_plate(String a);

    @Query("SELECT h FROM hospital_data h JOIN h.ambulance a WHERE a.ambulance_id = :id")
    public Hospital_data_class findhospitalbyambulance(@Param("id") String id);
}
