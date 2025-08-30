package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface Ambulance_details_repo extends JpaRepository<Ambulance_data_class , String> {
}
