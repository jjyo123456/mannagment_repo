package com.example.mannagment.mannagment.main_file.doctor_db.Patient_table;

import com.example.mannagment.mannagment.auth_file.Auth_repository;
import com.example.mannagment.mannagment.auth_file.user_object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
class Patient_info_servie {

    private final PatientRepository patientRepository;
    private final Auth_repository authRepository;

    @Autowired
    public Patient_info_servie(PatientRepository patientRepository, Auth_repository authRepository) {
        this.patientRepository = patientRepository;
        this.authRepository = authRepository;
    }

    // ✅ 1. Create a new patient (linked to existing user)
    @Transactional
    public Patient createPatient(Patient patient, String userId) {
        user_object user = authRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        patient.setUser(user);
        return patientRepository.save(patient);
    }

    // ✅ 2. Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // ✅ 3. Get patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }

    // ✅ 4. Update patient info (DOB, gender, blood group)
    @Transactional
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);

        existing.setDob(updatedPatient.getDob());
        existing.setGender(updatedPatient.getGender());
        existing.setBloodGroup(updatedPatient.getBloodGroup());

        return patientRepository.save(existing);
    }

    // ✅ 5. Delete patient
    @Transactional
    public void deletePatient(Long id) {
        Patient existing = getPatientById(id);
        patientRepository.delete(existing);
    }

    // ✅ 6. Get patient by user_id
    public Patient getPatientByUserId(String userId) {
        return patientRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("No patient found for user ID: " + userId));
    }
}

