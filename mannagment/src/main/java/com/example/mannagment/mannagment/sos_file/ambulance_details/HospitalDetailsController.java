package com.example.mannagment.mannagment.sos_file.ambulance_details;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalDetailsController {

    private final HospitalDetailsService hospitalService;

    public HospitalDetailsController(HospitalDetailsService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<Hospital_data_class> createHospital(@RequestBody Hospital_data_class hospital) {
        return ResponseEntity.ok(hospitalService.createHospital(hospital));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital_data_class> getHospital(@PathVariable Long id) {
        return hospitalService.getHospitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Hospital_data_class>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital_data_class> updateHospital(@PathVariable Long id, @RequestBody Hospital_data_class updatedHospital) {
        return hospitalService.updateHospital(id, updatedHospital)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        return hospitalService.deleteHospital(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-ambulance-id/{ambulanceId}")
    public ResponseEntity<Hospital_data_class> getByAmbulanceId(@PathVariable String ambulanceId) {
        return hospitalService.getHospitalByAmbulanceId(ambulanceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-ambulance-number/{numberPlate}")
    public ResponseEntity<Hospital_data_class> getByNumberPlate(@PathVariable String numberPlate) {
        return hospitalService.getHospitalByAmbulanceNumberPlate(numberPlate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-ambulance-driver/{driverName}")
    public ResponseEntity<Hospital_data_class> getByDriverName(@PathVariable String driverName) {
        return hospitalService.getHospitalByAmbulanceDriverName(driverName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

