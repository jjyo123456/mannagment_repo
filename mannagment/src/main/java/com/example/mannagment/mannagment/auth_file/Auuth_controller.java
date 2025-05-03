package com.example.mannagment.mannagment.auth_file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class Auuth_controller {
Auth_repository repository;

@Autowired
public Auuth_controller(Auth_repository repository) {
    this.repository = repository;
}

@ExceptionHandler(ErrorException.class)
public ResponseEntity<Erroe_response_object> exceptionhandler(ErrorException error){
    Erroe_response_object error_response_object = new Erroe_response_object();
    error_response_object.setMessage(error.getMessage());
    error_response_object.setStatus("hehehehehe");
    error_response_object.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    return new ResponseEntity<>(error_response_object, HttpStatus.NOT_FOUND);
}

@PostMapping("/signup")
public void sign_up(@RequestBody user_object new_userr){
    this.repository.save(new_userr);
}

@GetMapping("/login/{name}")
public user_object login_method(@PathVariable("name") String name){
    user_object found = this.repository.findbyname(name);
    if (found == null) {
        throw new ErrorException("User not found");
    }
    return found;
}
}
