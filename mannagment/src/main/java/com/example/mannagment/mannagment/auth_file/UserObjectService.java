package com.example.mannagment.mannagment.auth_file;

import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info;
import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info_repository;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserObjectService {

    private final Auth_repository authRepository;
    private final PatientRepository patientRepository;
    private final Doctor_info_repository doctorRepository;

    @Autowired
    public UserObjectService(Auth_repository authRepository,
                             PatientRepository patientRepository,
                             Doctor_info_repository doctorRepository) {
        this.authRepository = authRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public user_object registerUser(user_object user) {
        // 1️⃣ Save the user_object
        user_object savedUser = authRepository.save(user);

        // 2️⃣ Automatically create Patient record if role contains PATIENT
        if (savedUser.getRoles().contains(user_object.Role.PATIENT)) {
            Patient patient = new Patient();
            patient.setUser(savedUser); // link FK to user_object
            patientRepository.save(patient);
        }

        // 3️⃣ Automatically create Doctor record if role contains DOCTOR
        if (savedUser.getRoles().contains(user_object.Role.DOCTOR)) {
            Doctor_info doctor = new Doctor_info();
            doctor.setUser(savedUser); // link FK to user_object
            doctorRepository.save(doctor);
        }

        return savedUser;
    }

    // Optional: Find user by email
    public Optional<user_object> findByEmail(String email) {
        return Optional.ofNullable(authRepository.findByEmail(email));
    }

    // Optional: Update roles and create corresponding Patient/Doctor entries
    @Transactional
    public user_object addRole(UUID userId, user_object.Role role) {
        user_object user = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Add new role if not already present
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);

            // Link new role to appropriate table
            if (role == user_object.Role.PATIENT) {
                Patient patient = new Patient();
                patient.setUser(user);
                patientRepository.save(patient);
            } else if (role == user_object.Role.DOCTOR) {
                Doctor_info doctor = new Doctor_info();
                doctor.setUser(user);
                doctorRepository.save(doctor);
            }
        }

        return authRepository.save(user);
    }
}
