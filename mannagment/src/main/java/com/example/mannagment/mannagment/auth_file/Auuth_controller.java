package com.example.mannagment.mannagment.auth_file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("auth/")
public class Auuth_controller {

    Auth_repository repository;

    @Autowired
    public Auuth_controller(Auth_repository repository) {
        this.repository = repository;
    }
    @RequestMapping("signup/{new_user}")
    public void sign_up(@PathVariable("new_user") user_object new_userr){
        this.repository.save(new_userr);
    }
    @RequestMapping("login/{name}")
    public user_object login_method(@PathVariable("name") String name){
        return this.repository.findbyname(name);
    }
}
