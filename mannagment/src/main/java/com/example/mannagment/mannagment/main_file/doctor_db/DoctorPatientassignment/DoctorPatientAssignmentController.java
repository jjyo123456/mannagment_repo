package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment;

import com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-patient-assignments")
public class DoctorPatientAssignmentController {

    private final DoctorPatientAssignmentService assignmentService;

    public DoctorPatientAssignmentController(
            DoctorPatientAssignmentService assignmentService
    ) {
        this.assignmentService = assignmentService;
    }

    // --------------------------------------------------
    // CREATE / GRANT ACCESS (IDEMPOTENT)
    // --------------------------------------------------
    // Used when:
    // - Doctor starts treating patient
    // - Appointment is booked
    // - Manual admin linking
    // --------------------------------------------------

    @PostMapping
    public ResponseEntity<DoctorPatientAssignmentResponseDTO>
    createAssignment(
            @RequestBody DoctorPatientAssignmentCreateDTO dto
    ) {
        DoctorPatientAssignmentResponseDTO response =
                assignmentService.createAssignmentIfNotExists(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // --------------------------------------------------
    // ACCESS CHECK (SECURITY-CRITICAL)
    // --------------------------------------------------
    // Used internally by:
    // - Medical records APIs
    // - Prescription APIs
    // - Reports APIs
    // --------------------------------------------------

    @GetMapping("/access-check")
    public ResponseEntity<Boolean> checkDoctorAccessToPatient(
            @RequestParam Long doctorId,
            @RequestParam Long patientId
    ) {
        boolean hasAccess =
                assignmentService.hasDoctorAccessToPatient(
                        doctorId, patientId
                );

        return ResponseEntity.ok(hasAccess);
    }

    // --------------------------------------------------
    // DOCTOR-SIDE VIEW
    // --------------------------------------------------
    // Doctor dashboard → list of patients
    // --------------------------------------------------

    @GetMapping("/doctor/{doctorId}/patients")
    public ResponseEntity<List<DoctorAssignedPatientDTO>>
    getPatientsForDoctor(
            @PathVariable Long doctorId
    ) {
        List<DoctorAssignedPatientDTO> patients =
                assignmentService.getPatientsForDoctor(doctorId);

        return ResponseEntity.ok(patients);
    }

    // --------------------------------------------------
    // PATIENT-SIDE VIEW
    // --------------------------------------------------
    // Patient app → list of doctors
    // --------------------------------------------------

    @GetMapping("/patient/{patientId}/doctors")
    public ResponseEntity<List<PatientAssignedDoctorDTO>>
    getDoctorsForPatient(
            @PathVariable Long patientId
    ) {
        List<PatientAssignedDoctorDTO> doctors =
                assignmentService.getDoctorsForPatient(patientId);

        return ResponseEntity.ok(doctors);
    }
}
