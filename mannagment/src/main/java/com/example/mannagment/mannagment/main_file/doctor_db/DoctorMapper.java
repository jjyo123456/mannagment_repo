package com.example.mannagment.mannagment.main_file.doctor_db;

import com.example.mannagment.mannagment.main_file.doctor_db.Patient_table.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    // Map Doctor_info entity → DoctorDTO
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(source = "hospital.hospital_name", target = "hospitalName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "patients", target = "patientIds", qualifiedByName = "mapPatientIds")
    DoctorDTO toDTO(Doctor_info doctor);

    // Map DoctorDTO → Doctor_info entity
    @Mapping(source = "hospitalId", target = "hospital.id")
    @Mapping(target = "hospital", ignore = true) // keep hospital mapping manual
    @Mapping(target = "user", ignore = true)     // user mapping handled separately
    @Mapping(target = "patients", ignore = true) // patients mapping handled separately
    Doctor_info toEntity(DoctorDTO dto);

    // List mappings
    List<DoctorDTO> toDTOList(List<Doctor_info> doctors);
    List<Doctor_info> toEntityList(List<DoctorDTO> doctorDTOs);

    // Helper to map List<Patient> → List<Long> (patient IDs)
    @Named("mapPatientIds")
    default List<Long> mapPatientIds(List<Patient> patients) {
        if (patients == null) return null;
        return patients.stream().map(Patient::getId).collect(Collectors.toList());
    }
}
