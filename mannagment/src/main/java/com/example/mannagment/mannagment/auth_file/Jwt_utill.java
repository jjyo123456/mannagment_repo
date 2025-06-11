package com.example.mannagment.mannagment.auth_file;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt_utill {
    public String somethin_secret = "abc";

    public String extract_subject(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extract_expiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(somethin_secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isjwt_expired(String token) {
        return extract_expiration(token).before(new Date());
    }

    public boolean validatetoken(String token, UserDetails userDetails) {
        String username = extract_subject(token);
        return username.equals(userDetails.getUsername()) && !isjwt_expired(token);  
    }

    public String genneratetoken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, somethin_secret.getBytes())
                .compact();  
    }
}
