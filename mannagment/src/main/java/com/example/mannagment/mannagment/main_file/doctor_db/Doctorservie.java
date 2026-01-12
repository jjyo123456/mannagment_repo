package com.example.mannagment.mannagment.main_file.doctor_db;

import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import com.example.mannagment.mannagment.sos_file.ambulance_details.Hospital_data_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Doctorservie {

    private final Doctor_info_repository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public Doctorservie(Doctor_info_repository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    // Get all doctors
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor_info> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> {
            DoctorDTO dto = doctorMapper.toDTO(doctor);
            // populate patientIds
            dto.setPatientIds(
                    doctor.getPatients() != null ?
                            doctor.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
            );
            return dto;
        }).collect(Collectors.toList());
    }

    // Get doctor by ID
    public DoctorDTO getDoctorById(Long id) {
        Optional<Doctor_info> doctor = doctorRepository.findById(id);
        return doctor.map(d -> {
            DoctorDTO dto = doctorMapper.toDTO(d);
            dto.setPatientIds(
                    d.getPatients() != null ?
                            d.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
            );
            return dto;
        }).orElse(null);
    }

    // Create a new doctor
    public DoctorDTO createDoctor(DoctorDTO dto) {
        Doctor_info doctorEntity = doctorMapper.toEntity(dto);
        // Handle user and patients manually if needed
        Doctor_info savedDoctor = doctorRepository.save(doctorEntity);
        return doctorMapper.toDTO(savedDoctor);
    }

    // Update existing doctor
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Optional<Doctor_info> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new RuntimeException("Doctor not found with ID: " + id);
        }

        Doctor_info existingDoctor = optionalDoctor.get();
        existingDoctor.setName(dto.getName());
        existingDoctor.setSpecialty(dto.getSpecialty());
        existingDoctor.setConsultationFee(dto.getConsultationFee());
        existingDoctor.setYearsOfExperience(dto.getYearsOfExperience());
        existingDoctor.setHospital(dto.getHospitalId() != null ? new Hospital_data_class(dto.getHospitalId()) : null);

        Doctor_info updatedDoctor = doctorRepository.save(existingDoctor);
        DoctorDTO updatedDTO = doctorMapper.toDTO(updatedDoctor);

        // Populate patientIds
        updatedDTO.setPatientIds(
                updatedDoctor.getPatients() != null ?
                        updatedDoctor.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
        );

        return updatedDTO;
    }

    // Delete doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Fetch doctors by specialty
    public List<DoctorDTO> getDoctorsBySpecialty(String specialty) {
        List<Doctor_info> doctors = doctorRepository.findBySpecialty(specialty);
        return doctors.stream().map(doctor -> {
            DoctorDTO dto = doctorMapper.toDTO(doctor);
            dto.setPatientIds(
                    doctor.getPatients() != null ?
                            doctor.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
            );
            return dto;
        }).collect(Collectors.toList());
    }

    // Search doctors by name
    public List<DoctorDTO> searchDoctorsByName(String keyword) {
        List<Doctor_info> doctors = doctorRepository.findByNameContainingIgnoreCase(keyword);
        return doctors.stream().map(doctor -> {
            DoctorDTO dto = doctorMapper.toDTO(doctor);
            dto.setPatientIds(
                    doctor.getPatients() != null ?
                            doctor.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
            );
            return dto;
        }).collect(Collectors.toList());
    }

    // Fetch doctors by hospital
    public List<DoctorDTO> getDoctorsByHospital(Hospital_data_class hospital) {
        List<Doctor_info> doctors = doctorRepository.findByHospital(hospital);
        return doctors.stream().map(doctor -> {
            DoctorDTO dto = doctorMapper.toDTO(doctor);
            dto.setPatientIds(
                    doctor.getPatients() != null ?
                            doctor.getPatients().stream().map(Patient::getId).collect(Collectors.toList()) : null
            );
            return dto;
        }).collect(Collectors.toList());
    }
}
