package com.example.mannagment.mannagment.sos_file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/SOS")
public class sos_controller {

    @Autowired
    public Token_service token_service;
    @GetMapping("/twilio_token")
    public ResponseEntity token_gennerator(){
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        String userid = null;
        if(principal instanceof UserDetails) {
         return ResponseEntity.status(HttpStatus.OK).body(token_service.gennerate_token(((UserDetails) principal).getUsername()));
        }
        else{

        }
    }
}
