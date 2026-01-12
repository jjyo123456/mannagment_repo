package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Ambulance_data_class add_ambulance_data(Ambulance_data_class data){
        ambulanceDetailsRepo.save(data);
        return data;
    }
    public Ambulance_data_class update_ambulance_data(Ambulance_data_class updated_dataa){
        ambulanceDetailsRepo.save(updated_dataa);
        return updated_dataa;
    }

    Ambulance_data_class get_ambulance_data_by_id(String id){
        return ambulanceDetailsRepo.getReferenceById(id);
    }

    Ambulance_data_class update_status_ambulance(ambulance_enum enumm, String id){
        Ambulance_data_class a = ambulanceDetailsRepo.getReferenceById(id);
        a.setStatus(enumm);
        return ambulanceDetailsRepo.save(a);
    }


    Ambulance_data_class get_details_by_driver_name(String name){
        Ambulance_data_class a = ambulanceDetailsRepo.findBydriver_name(name);
        return a;
    }

    Ambulance_data_class get_details_by_number_plate(String number){
        Ambulance_data_class a = ambulanceDetailsRepo.findBynumber_plate(number);
        return a;
    }

   public Ambulance_data_class findNearestAvailableAmbulance(double lat, double lon){
        List<Ambulance_data_class> list = ambulanceDetailsRepo.findAllBystatus(ambulance_enum.available);

        Ambulance_data_class nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Ambulance_data_class amb : list) {
            double dist = haversine(lat, lon, amb.getLatitude(), amb.getLongitude());
            if (dist < minDistance) {
                minDistance = dist;
                nearest = amb;
            }
        }
        return nearest;
    }

    public Hospital_data_class findHospitalByAmbulanceId(String ambulanceId) {
        return ambulanceDetailsRepo.findhospitalbyambulance(ambulanceId);
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }


}
