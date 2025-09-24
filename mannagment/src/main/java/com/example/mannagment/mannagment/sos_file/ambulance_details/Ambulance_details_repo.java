package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface Ambulance_details_repo extends JpaRepository<Ambulance_data_class , String> {

    List<Ambulance_data_class> findAllBystatus(ambulance_enum enumm);

    Ambulance_data_class findBydriver_name(String name);

    Ambulance_data_class findBynumber_plate(String a);
}
