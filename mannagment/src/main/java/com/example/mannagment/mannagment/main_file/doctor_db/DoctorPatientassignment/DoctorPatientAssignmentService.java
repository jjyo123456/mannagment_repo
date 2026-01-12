package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment;

import com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment.dto.*;

import java.util.List;

public interface DoctorPatientAssignmentService {

    // Create only if not exists
    DoctorPatientAssignmentResponseDTO createAssignmentIfNotExists(
            DoctorPatientAssignmentCreateDTO dto
    );

    // Hard access check
    boolean hasDoctorAccessToPatient(Long doctorId, Long patientId);

    // Doctor dashboard
    List<DoctorAssignedPatientDTO> getPatientsForDoctor(Long doctorId);

    // Patient dashboard
    List<PatientAssignedDoctorDTO> getDoctorsForPatient(Long patientId);
}
