package main.java.com.example.mannagment.mannagment.auth_file;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    public Jwt_utill Jwtutil;

    @Autowired
    public UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader = request.getHeader("authentication");

        if(authheader != null && authheader.contains("bearer")){
            String jwt = authheader.substring(7);
            String username = Jwtutil.extract_subject(jwt);
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userdetails = userDetailsService.loadUserByUsername(username);
        }
    }
    }
}
