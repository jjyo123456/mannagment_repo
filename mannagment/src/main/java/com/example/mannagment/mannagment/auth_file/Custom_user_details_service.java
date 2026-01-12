package com.example.mannagment.mannagment.auth_file;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Custom_user_details_service implements UserDetailsService{
    @Autowired
    Auth_repository auth_repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user_object user = auth_repository.findbyname(username);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities_custom_functiom(user));
    }

    private Collection<? extends GrantedAuthority> authorities_custom_functiom(user_object user){
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
