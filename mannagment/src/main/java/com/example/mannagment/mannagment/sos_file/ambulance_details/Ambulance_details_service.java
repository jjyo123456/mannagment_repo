package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ambulance_details_service {

    @Autowired
    private Ambulance_details_repo ambulanceDetailsRepo;

    @Autowired
    private DTO_mapper_class_ambulbance_data mapper;

    Ambulance_details_service(Ambulance_details_repo ambulanceDetailsRepo , DTO_mapper_class_ambulbance_data mapper){
        this.ambulanceDetailsRepo = ambulanceDetailsRepo;
        this.mapper = mapper;
    }

    private Ambulance_request_dto add_ambulance_data(Ambulance_data_class entity){
        Ambulance_request_dto dto_object = new Ambulance_request_dto();
        dto_object = mapper.toDto(entity);
        ambulanceDetailsRepo.save(dto_object);
        return
    }

}
