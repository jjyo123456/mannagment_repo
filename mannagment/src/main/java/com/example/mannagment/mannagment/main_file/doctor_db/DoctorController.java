package com.example.mannagment.mannagment.main_file.doctor_db;

import com.example.mannagment.mannagment.sos_file.ambulance_details.Hospital_data_class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final Doctorservie doctorService;

    @Autowired
    public DoctorController(Doctorservie doctorService) {
        this.doctorService = doctorService;
    }

    // 1️⃣ Get all doctors
    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // 2️⃣ Get doctor by ID
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    // 3️⃣ Create a new doctor
    @PostMapping
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.createDoctor(doctorDTO);
    }

    // 4️⃣ Update an existing doctor
    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(id, doctorDTO);
    }

    // 5️⃣ Delete a doctor
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    // 6️⃣ Find doctors by specialty
    @GetMapping("/specialty/{specialty}")
    public List<DoctorDTO> getDoctorsBySpecialty(@PathVariable String specialty) {
        return doctorService.getDoctorsBySpecialty(specialty);
    }

    // 7️⃣ Search doctors by name (partial match)
    @GetMapping("/search")
    public List<DoctorDTO> searchDoctors(@RequestParam("keyword") String keyword) {
        return doctorService.searchDoctorsByName(keyword);
    }

    // 8️⃣ Optional: Get doctors by hospital
    @GetMapping("/hospital/{hospitalId}")
    public List<DoctorDTO> getDoctorsByHospital(@PathVariable Long hospitalId) {
        return doctorService.getDoctorsByHospital(new Hospital_data_class(hospitalId));
    }
}
