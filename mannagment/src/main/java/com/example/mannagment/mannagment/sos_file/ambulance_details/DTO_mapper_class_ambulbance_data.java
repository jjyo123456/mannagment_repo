package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.stereotype.Component;

@Component
public class DTO_mapper_class_ambulbance_data {

    // Convert DTO -> Entity (for saving into DB)
    public Ambulance_data_class toEntity(Ambulance_request_dto dto) {
        Ambulance_data_class ambulance = new Ambulance_data_class();
        ambulance.setHospital_id(dto.getHospitalId());
        ambulance.setNumber_plate(dto.getNumberPlate());
        ambulance.setDriver_name(dto.getDriverName());
        ambulance.setDriver_contact(dto.getDriverContact());
        ambulance.setLatitude(dto.getLatitude());
        ambulance.setLongitude(dto.getLongitude());
        return ambulance;
    }

    // Convert Entity -> DTO (for passing back in service layer if needed)
    public Ambulance_request_dto toDto(Ambulance_data_class entity) {
        Ambulance_request_dto dto = new Ambulance_request_dto();
        dto.setHospitalId(entity.getHospital_id());
        dto.setNumberPlate(entity.getAmbulance_id());
        dto.setDriverName(entity.getDriver_name());
        dto.setDriverContact(entity.getDriver_contact());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;
    }
}
