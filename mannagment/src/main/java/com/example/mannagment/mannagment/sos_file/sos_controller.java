package com.example.mannagment.mannagment.sos_file;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController("/SOS")
public class sos_controller {

    @Autowired
    public Token_service token_service;

    @ExceptionHandler(Token_error.class)
    public ResponseEntity<Token_error_object> token_exception_handellar(Token_error ex){
        Token_error_object error  = new Token_error_object(ex.getMessage() , HttpStatus.NOT_FOUND , new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/twilio_token")
    public ResponseEntity token_gennerator(){
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        String userid = null;
        if(principal instanceof UserDetails) {
         return ResponseEntity.status(HttpStatus.OK).body(token_service.gennerate_token(((UserDetails) principal).getUsername()));
        }
        else{
            throw new Token_error("hehe");
        }
    }
}
