package com.example.mannagment.mannagment.auth_file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Auth_repository extends JpaRepository<user_object, UUID> {

    // Find user by name
    user_object findByName(String name);

    // Find user by email (optional, useful for login)
    user_object findByEmail(String email);
}
