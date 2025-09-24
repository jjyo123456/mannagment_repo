package com.example.mannagment.mannagment.sos_file.ambulance_details;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalDetailsService {

    private final Hopsital_details_repo hospitalRepo;

    public HospitalDetailsService(Hopsital_details_repo hospitalRepo) {
        this.hospitalRepo = hospitalRepo;
    }

    public Hospital_data_class createHospital(Hospital_data_class hospital) {
        hospital.setCreatedAt(LocalDateTime.now());
        hospital.setUpdatedAt(LocalDateTime.now());
        return hospitalRepo.save(hospital);
    }

    public Optional<Hospital_data_class> getHospitalById(Long id) {
        return hospitalRepo.findById(id);
    }

    public List<Hospital_data_class> getAllHospitals() {
        return hospitalRepo.findAll();
    }

    public Optional<Hospital_data_class> getHospitalByAmbulanceId(String ambulanceId) {
        return Optional.ofNullable(hospitalRepo.findbyambualnceid(ambulanceId));
    }

    public Optional<Hospital_data_class> getHospitalByAmbulanceNumberPlate(String numberPlate) {
        return Optional.ofNullable(hospitalRepo.findbyamulancenumber_plate(numberPlate));
    }

    public Optional<Hospital_data_class> getHospitalByAmbulanceDriverName(String driverName) {
        return Optional.ofNullable(hospitalRepo.findbyamulancedriver_name(driverName));
    }

    @Transactional
    public Optional<Hospital_data_class> updateHospital(Long id, Hospital_data_class updatedHospital) {
        return hospitalRepo.findById(id).map(hospital -> {
            hospital.setHospitalName(updatedHospital.getHospitalName());
            hospital.setAddress(updatedHospital.getAddress());
            hospital.setLatitude(updatedHospital.getLatitude());
            hospital.setLongitude(updatedHospital.getLongitude());
            hospital.setContactNumber(updatedHospital.getContactNumber());
            hospital.setUpdatedAt(LocalDateTime.now());
            return hospital;
        });
    }

    public boolean deleteHospital(Long id) {
        return hospitalRepo.findById(id).map(hospital -> {
            hospitalRepo.delete(hospital);
            return true;
        }).orElse(false);
    }
}
