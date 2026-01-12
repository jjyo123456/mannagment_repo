package com.example.mannagment.mannagment.sos_file.ambulance_request;


import com.example.mannagment.mannagment.sos_file.ambulance_details.Ambulance_data_class;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    // Create or update a request
    public Request_data_class saveRequest(Request_data_class request) {
        return requestRepository.save(request);
    }

    // Find request by ID
    public Optional<Request_data_class> getRequestById(String requestId) {
        return requestRepository.findById(requestId);
    }

    // Delete request by ID
    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }

    // Get all requests
    public List<Request_data_class> getAllRequests() {
        return requestRepository.findAll();
    }

    // Get all requests with their ambulance details
    public List<Request_data_class> getAllRequestsWithAmbulance() {
        return requestRepository.findAllWithAmbulance();
    }

    // Get requests assigned to a specific ambulance
    public List<Request_data_class> getRequestsByAmbulanceId(String ambulanceId) {
        return requestRepository.findRequestsByAmbulanceId(ambulanceId);
    }

    // Find a request by user ID
    public Optional<Request_data_class> getRequestByUserId(String userId) {
        return requestRepository.findByUserId(userId);
    }

    // Count requests for a given ambulance
    public long countRequestsByAmbulanceId(String ambulanceId) {
        return requestRepository.countRequestsByAmbulanceId(ambulanceId);
    }

    // Get ambulance details for a given request
    public Ambulance_data_class getAmbulanceByRequestId(String requestId) {
        return requestRepository.findAmbulanceByRequestId(requestId);
    }

    // Get all pending requests (if status column exists)
    public List<Request_data_class> getPendingRequests() {
        return requestRepository.findPendingRequests();
    }
}

