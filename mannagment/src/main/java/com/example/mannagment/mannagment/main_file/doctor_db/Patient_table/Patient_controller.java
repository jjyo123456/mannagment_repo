package com.example.mannagment.mannagment.main_file.doctor_db.Patient_table;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class Patient_controller {

    private final Patient_info_servie patientService;

    @Autowired
    public Patient_controller(Patient_info_servie patientService) {
        this.patientService = patientService;
    }

    // ✅ 1. Create a new patient (linked to existing user)
    // Example: POST /api/patients/create?userId=123e4567
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(
            @RequestParam("userId") String userId,
            @RequestBody Patient patient) {

        Patient savedPatient = patientService.createPatient(patient, userId);
        return ResponseEntity.ok(savedPatient);
    }

    // ✅ 2. Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // ✅ 3. Get a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // ✅ 4. Update patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        Patient updated = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updated);
    }

    // ✅ 5. Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with ID " + id + " deleted successfully.");
    }
}

// ✅ 6. Get patient by linked

