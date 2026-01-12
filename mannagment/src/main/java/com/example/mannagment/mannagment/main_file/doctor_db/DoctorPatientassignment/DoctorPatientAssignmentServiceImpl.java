package com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment;

import com.example.mannagment.mannagment.main_file.doctor_db.DoctorPatientassignment.dto.*;
import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info;
import com.example.mannagment.mannagment.main_file.doctor_db.Doctor_info_repository;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorPatientAssignmentServiceImpl
        implements DoctorPatientAssignmentService {

    private final DoctorPatientAssignmentRepository assignmentRepository;
    private final Doctor_info_repository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorPatientAssignmentServiceImpl(
            DoctorPatientAssignmentRepository assignmentRepository,
            Doctor_info_repository doctorRepository,
            PatientRepository patientRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    // --------------------------------------------------
    // CREATE ASSIGNMENT (SAFE & IDEMPOTENT)
    // --------------------------------------------------

    @Override
    public DoctorPatientAssignmentResponseDTO createAssignmentIfNotExists(
            DoctorPatientAssignmentCreateDTO dto
    ) {
        boolean exists = assignmentRepository
                .existsByDoctor_IdAndPatient_Id(
                        dto.getDoctorId(),
                        dto.getPatientId()
                );

        if (exists) {
            return assignmentRepository
                    .findByDoctor_IdAndPatient_Id(
                            dto.getDoctorId(),
                            dto.getPatientId()
                    )
                    .map(this::mapToResponseDTO)
                    .orElseThrow();
        }

        Doctor_info doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Patient not found"));

        Doctor_patient_assignment assignment =
                new Doctor_patient_assignment();

        assignment.setDoctor(doctor);
        assignment.setPatient(patient);

        Doctor_patient_assignment saved =
                assignmentRepository.save(assignment);

        return mapToResponseDTO(saved);
    }

    // --------------------------------------------------
    // ACCESS CONTROL (CRITICAL)
    // --------------------------------------------------

    @Override
    @Transactional(readOnly = true)
    public boolean hasDoctorAccessToPatient(Long doctorId, Long patientId) {
        return assignmentRepository
                .hasDoctorAccessToPatient(doctorId, patientId);
    }

    // --------------------------------------------------
    // DOCTOR-SIDE VIEW
    // --------------------------------------------------

    @Override
    @Transactional(readOnly = true)
    public List<DoctorAssignedPatientDTO> getPatientsForDoctor(Long doctorId) {

        return assignmentRepository
                .findAllByDoctor_Id(doctorId)
                .stream()
                .map(assignment -> {
                    Patient patient = assignment.getPatient();

                    DoctorAssignedPatientDTO dto =
                            new DoctorAssignedPatientDTO();

                    dto.setPatientId(patient.getId());
                    dto.setPatientName(
                            patient.getUser().getName()
                    );
                    dto.setGender(
                            patient.getGender().name()
                    );
                    dto.setBloodGroup(
                            patient.getBloodGroup()
                    );
                    dto.setAssignedAt(
                            assignment.getAssignedAt()
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // --------------------------------------------------
    // PATIENT-SIDE VIEW
    // --------------------------------------------------

    @Override
    @Transactional(readOnly = true)
    public List<PatientAssignedDoctorDTO> getDoctorsForPatient(Long patientId) {

        return assignmentRepository
                .findAllByPatient_Id(patientId)
                .stream()
                .map(assignment -> {
                    Doctor_info doctor = assignment.getDoctor();

                    PatientAssignedDoctorDTO dto =
                            new PatientAssignedDoctorDTO();

                    dto.setDoctorId(doctor.getId());
                    dto.setDoctorName(doctor.getName());
                    dto.setSpecialty(doctor.getSpecialty());
                    dto.setYearsOfExperience(
                            doctor.getYearsOfExperience()
                    );
                    dto.setAssignedAt(
                            assignment.getAssignedAt()
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // --------------------------------------------------
    // INTERNAL MAPPER
    // --------------------------------------------------

    private DoctorPatientAssignmentResponseDTO mapToResponseDTO(
            Doctor_patient_assignment assignment
    ) {
        DoctorPatientAssignmentResponseDTO dto =
                new DoctorPatientAssignmentResponseDTO();

        dto.setAssignmentId(assignment.getId());
        dto.setDoctorId(assignment.getDoctor().getId());
        dto.setDoctorName(assignment.getDoctor().getName());
        dto.setPatientId(assignment.getPatient().getId());
        dto.setPatientName(
                assignment.getPatient().getUser().getName()
        );
        dto.setAssignedAt(assignment.getAssignedAt());

        return dto;
    }
}
