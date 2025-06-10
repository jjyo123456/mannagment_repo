package com.example.mannagment.mannagment.auth_file;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Custom_user_details_service implements UserDetailsService{
    @Autowired
    Auth_repository auth_repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user_object user = auth_repository.findbyname(username);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
    }
}
