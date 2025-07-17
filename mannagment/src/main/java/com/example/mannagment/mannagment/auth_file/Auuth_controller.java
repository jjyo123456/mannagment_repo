package com.example.mannagment.mannagment.auth_file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Auuth_controller {
    Auth_repository repository;

    @Autowired
    public Auuth_controller(Auth_repository repository) {
        this.repository = repository;
    }

    @Autowired
    public Jwt_utill jwt_utill;

    @Autowired
    public Custom_user_details_service userdetailsservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Erroe_response_object> exceptionhandler(ErrorException error) {
        Erroe_response_object error_response_object = new Erroe_response_object();
        error_response_object.setMessage(error.getMessage());
        error_response_object.setStatus("hehehehehe");
        error_response_object.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return new ResponseEntity<>(error_response_object, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public void sign_up(@RequestBody user_object new_userr) {
        this.repository.save(new_userr);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userdetailsservice.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwt_utill.genneratetoken(userDetails);



        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
