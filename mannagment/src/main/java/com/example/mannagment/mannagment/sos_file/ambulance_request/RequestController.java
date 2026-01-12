package com.example.mannagment.mannagment.sos_file.ambulance_request;


import com.example.mannagment.mannagment.sos_file.ambulance_details.Ambulance_data_class;
import com.example.mannagment.mannagment.sos_file.ambulance_details.Ambulance_details_service;
import com.example.mannagment.mannagment.sos_file.ambulance_details.Hospital_data_class;
import com.example.mannagment.mannagment.sos_file.request_websocket.RequestController_websocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private final RequestService requestService;

    @Autowired
    private final Ambulance_details_service ambulanceDetailsService;

    @Autowired
    private final RequestController_websocket requestControllerWebsocket;



    public RequestController(RequestService requestService, Ambulance_details_service ambulanceDetailsService, RequestController_websocket requestControllerWebsocket) {
        this.requestService = requestService;
        this.ambulanceDetailsService = ambulanceDetailsService;
        this.requestControllerWebsocket = requestControllerWebsocket;

    }

    // Create a new request
    @PostMapping
    public ResponseEntity<Request_data_class> Assign_ambulance_save_request(@RequestBody Request_data_class request) {
        Ambulance_data_class assigned = ambulanceDetailsService.findNearestAvailableAmbulance(request.getLat() , request.getLon());
        request.setAmbulance_assigned(assigned);
        Hospital_data_class h = ambulanceDetailsService.findHospitalByAmbulanceId(assigned.getAmbulance_id());
        Long hospital_id = h.getId();
        requestControllerWebsocket.sendAmbulanceAlert(hospital_id , request);
        return ResponseEntity.ok(requestService.saveRequest(request));
    }

    // Get request by ID
    @GetMapping("/{id}")
    public ResponseEntity<Request_data_class> getRequestById(@PathVariable String id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete request by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable String id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    // Get all requests
    @GetMapping
    public ResponseEntity<List<Request_data_class>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    // Get all requests with ambulance details
    @GetMapping("/with-ambulance")
    public ResponseEntity<List<Request_data_class>> getAllRequestsWithAmbulance() {
        return ResponseEntity.ok(requestService.getAllRequestsWithAmbulance());
    }

    // Get requests by ambulance ID
    @GetMapping("/ambulance/{ambulanceId}")
    public ResponseEntity<List<Request_data_class>> getRequestsByAmbulanceId(@PathVariable String ambulanceId) {
        return ResponseEntity.ok(requestService.getRequestsByAmbulanceId(ambulanceId));
    }

    // Get request by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Request_data_class> getRequestByUserId(@PathVariable String userId) {
        return requestService.getRequestByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Count requests for an ambulance
    @GetMapping("/ambulance/{ambulanceId}/count")
    public ResponseEntity<Long> countRequestsByAmbulance(@PathVariable String ambulanceId) {
        return ResponseEntity.ok(requestService.countRequestsByAmbulanceId(ambulanceId));
    }

    // Get ambulance for a given request
    @GetMapping("/{requestId}/ambulance")
    public ResponseEntity<Ambulance_data_class> getAmbulanceByRequestId(@PathVariable String requestId) {
        return ResponseEntity.ok(requestService.getAmbulanceByRequestId(requestId));
    }

    // Get pending requests
    @GetMapping("/pending")
    public ResponseEntity<List<Request_data_class>> getPendingRequests() {
        return ResponseEntity.ok(requestService.getPendingRequests());
    }
}

