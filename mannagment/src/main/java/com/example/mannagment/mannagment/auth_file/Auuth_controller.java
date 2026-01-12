package com.example.mannagment.mannagment.auth_file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Auuth_controller {

    private final UserObjectService userObjectService;
    private final Jwt_utill jwtUtill;
    private final Custom_user_details_service userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public Auuth_controller(UserObjectService userObjectService,
                          Jwt_utill jwtUtill,
                          Custom_user_details_service userDetailsService,
                          AuthenticationManager authenticationManager) {
        this.userObjectService = userObjectService;
        this.jwtUtill = jwtUtill;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    // ✅ Exception handler
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Erroe_response_object> handleException(ErrorException error) {
        Erroe_response_object response = new Erroe_response_object();
        response.setMessage(error.getMessage());
        response.setStatus("ERROR");
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ✅ Signup endpoint — automatically links Patient/Doctor based on roles
    @PostMapping("/signup")
    public ResponseEntity<user_object> signUp(@RequestBody user_object newUser) {
        user_object savedUser = userObjectService.registerUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT
        final String jwt = jwtUtill.genneratetoken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
