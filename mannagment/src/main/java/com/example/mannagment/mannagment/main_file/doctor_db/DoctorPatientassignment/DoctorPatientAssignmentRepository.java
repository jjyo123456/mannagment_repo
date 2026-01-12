package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorPatientAssignmentRepository
        extends JpaRepository<Doctor_patient_assignment, Long> {

    // --------------------------------------------------
    // CORE SAFETY CHECKS
    // --------------------------------------------------

    /**
     * Prevent duplicate doctor-patient trust creation.
     * Used before creating assignment.
     */
    boolean existsByDoctor_IdAndPatient_Id(Long doctorId, Long patientId);

    /**
     * Fetch exact assignment if it exists.
     * Used for access validation.
     */
    Optional<Doctor_patient_assignment> findByDoctor_IdAndPatient_Id(
            Long doctorId,
            Long patientId
    );

    // --------------------------------------------------
    // DOCTOR-SIDE QUERIES
    // --------------------------------------------------

    /**
     * Fetch all patients assigned to a doctor.
     * Used for doctor dashboard, patient list, EMR access.
     */
    List<Doctor_patient_assignment> findAllByDoctor_Id(Long doctorId);

    /**
     * Fast count for dashboards / limits / analytics.
     */
    long countByDoctor_Id(Long doctorId);

    // --------------------------------------------------
    // PATIENT-SIDE QUERIES
    // --------------------------------------------------

    /**
     * Fetch all doctors assigned to a patient.
     * Used for appointment booking & history.
     */
    List<Doctor_patient_assignment> findAllByPatient_Id(Long patientId);

    /**
     * Fast count for patient profile insights.
     */
    long countByPatient_Id(Long patientId);

    // --------------------------------------------------
    // SECURITY / ACCESS CONTROL
    // --------------------------------------------------

    /**
     * HARD ACCESS CHECK.
     * Use this before allowing document / history access.
     */
    @Query("""
        SELECT COUNT(dpa) > 0
        FROM Doctor_patient_assignment dpa
        WHERE dpa.doctor.id = :doctorId
          AND dpa.patient.id = :patientId
    """)
    boolean hasDoctorAccessToPatient(
            @Param("doctorId") Long doctorId,
            @Param("patientId") Long patientId
    );

    // --------------------------------------------------
    // FUTURE-PROOFING (OPTIONAL BUT SAFE)
    // --------------------------------------------------

    /**
     * Fetch assignments created after a specific time.
     * Useful for audits, analytics, reporting.
     */
    List<Doctor_patient_assignment> findAllByAssignedAtAfter(
            java.time.LocalDateTime timestamp
    );
}
