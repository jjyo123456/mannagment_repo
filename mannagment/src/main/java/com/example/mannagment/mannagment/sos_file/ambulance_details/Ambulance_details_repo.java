package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ambulance_details_repo extends JpaRepository<Ambulance_request_dto , String> {
}
