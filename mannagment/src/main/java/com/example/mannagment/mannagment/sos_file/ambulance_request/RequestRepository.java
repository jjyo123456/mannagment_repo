package com.example.mannagment.mannagment.sos_file.ambulance_request;

import com.example.mannagment.mannagment.sos_file.ambulance_details.Ambulance_data_class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request_data_class, String> {

    // Find all requests assigned to a specific ambulance
    @Query("SELECT r FROM Request_data_class r JOIN FETCH r.ambulance_assigned a WHERE a.ambulance_id = :ambulanceId")
    List<Request_data_class> findRequestsByAmbulanceId(@Param("ambulanceId") String ambulanceId);

    // Find a request by user id (assuming each user has at most one active request)
    Optional<Request_data_class> findByUserId(String userId);

    // Fetch all requests with their assigned ambulance details (avoids N+1 queries)
    @Query("SELECT r FROM Request_data_class r JOIN FETCH r.ambulance_assigned")
    List<Request_data_class> findAllWithAmbulance();

    // Count how many requests are assigned to a given ambulance
    @Query("SELECT COUNT(r) FROM Request_data_class r WHERE r.ambulance_assigned.ambulance_id = :ambulanceId")
    long countRequestsByAmbulanceId(@Param("ambulanceId") String ambulanceId);

    // Native SQL: Get ambulance details directly for a given request
    @Query(value = "SELECT a.* FROM ambulance_data_class a " +
            "JOIN request_data_class r ON r.ambulance_id = a.ambulance_id " +
            "WHERE r.request_id = :requestId",
            nativeQuery = true)
    Ambulance_data_class findAmbulanceByRequestId(@Param("requestId") String requestId);

    // Native SQL: Get all requests marked as pending (if a status column exists)
    @Query(value = "SELECT * FROM request_data_class r WHERE r.status = 'PENDING'", nativeQuery = true)
    List<Request_data_class> findPendingRequests();
}

