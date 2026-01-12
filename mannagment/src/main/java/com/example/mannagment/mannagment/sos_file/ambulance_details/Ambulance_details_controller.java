package com.example.mannagment.mannagment.sos_file.ambulance_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ambulance")
public class Ambulance_details_controller {

    @Autowired
    private Ambulance_details_service ambulanceService;


    @GetMapping("/{id}")
    public ResponseEntity<Ambulance_data_class> getAmbulanceById(@PathVariable String id) {
        Ambulance_data_class amb = ambulanceService.get_ambulance_data_by_id(id);
        return (amb != null) ? ResponseEntity.ok(amb) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Ambulance_data_class> addAmbulance(@RequestBody Ambulance_data_class data) {
        Ambulance_data_class saved = ambulanceService.add_ambulance_data(data);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ambulance_data_class> updateAmbulance(
            @PathVariable String id,
            @RequestBody Ambulance_data_class updatedData) {

        updatedData.setAmbulance_id(id); // Ensure same ID
        Ambulance_data_class updated = ambulanceService.update_ambulance_data(updatedData);
        return ResponseEntity.ok(updated);
    }

    // ✅ Update status only
    @PatchMapping("/{id}/status")
    public ResponseEntity<Ambulance_data_class> updateStatus(
            @PathVariable String id,
            @RequestParam ambulance_enum status) {

        Ambulance_data_class updated = ambulanceService.update_status_ambulance(status, id);
        return ResponseEntity.ok(updated);
    }

    // ✅ Search by driver name
    @GetMapping("/driver/{name}")
    public ResponseEntity<Ambulance_data_class> getByDriverName(@PathVariable String name) {
        Ambulance_data_class amb = ambulanceService.get_details_by_driver_name(name);
        return (amb != null) ? ResponseEntity.ok(amb) : ResponseEntity.notFound().build();
    }

    // ✅ Search by number plate
    @GetMapping("/plate/{number}")
    public ResponseEntity<Ambulance_data_class> getByNumberPlate(@PathVariable String number) {
        Ambulance_data_class amb = ambulanceService.get_details_by_number_plate(number);
        return (amb != null) ? ResponseEntity.ok(amb) : ResponseEntity.notFound().build();
    }

    // ✅ Find nearest available ambulance
    @GetMapping("/nearest")
    public ResponseEntity<Ambulance_data_class> findNearest(
            @RequestParam double lat,
            @RequestParam double lon) {

        Ambulance_data_class nearest = ambulanceService.findNearestAvailableAmbulance(lat, lon);
        return (nearest != null) ? ResponseEntity.ok(nearest) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-ambulance/{ambulanceId}")
    public ResponseEntity<Hospital_data_class> getHospitalByAmbulanceId(@PathVariable String ambulanceId) {
        Hospital_data_class hospital = ambulanceService.findHospitalByAmbulanceId(ambulanceId);
        return hospital != null ? ResponseEntity.ok(hospital) : ResponseEntity.notFound().build();
    }


}
