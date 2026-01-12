package com.example.mannagment.mannagment.main_file.doctor_db;


import com.example.mannagment.mannagment.sos_file.ambulance_details.Hospital_data_class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Doctor_info_repository extends JpaRepository<Doctor_info, Long> {

    // Find doctors by exact name
    List<Doctor_info> findByName(String name);

    // Find all doctors in a specific hospital
    List<Doctor_info> findByHospital(Hospital_data_class hospital);

    // Find doctors by specialty
    List<Doctor_info> findBySpecialty(String specialty);

    // Find doctors by specialty and hospital
    List<Doctor_info> findBySpecialtyAndHospital(String specialty, Hospital_data_class hospital);

    // Search by partial name (like) - useful for search page
    List<Doctor_info> findByNameContainingIgnoreCase(String keyword);

    // Optional: find doctors with consultation fee less than a value
    List<Doctor_info> findByConsultationFeeLessThan(Double fee);

    // Optional: find doctors with experience greater than a value
    List<Doctor_info> findByYearsOfExperienceGreaterThan(Integer years);

    // Custom query to fetch doctors with sorting by experience or fee
    @Query("SELECT d FROM Doctor_info_db d WHERE d.speciality = :spceiality_required ORCER BY d.yearsOfExperience DESC")
    List<Doctor_info> findbyspecielityandorderedbyyearsofexperience(@Param("speciality_required") String speciality);
}

