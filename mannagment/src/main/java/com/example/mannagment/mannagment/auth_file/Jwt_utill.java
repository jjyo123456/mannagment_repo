package main.java.com.example.mannagment.mannagment.auth_file;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class Jwt_utill {
    public String somethin_secret = "abc";

    public String extract_subject(String token){
        return extractAllClaims(token).getSubject();
    }
    public String extract_expiration(String token){
        return extractAllClaims(token).getExpiration();
    }
    
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(somethin_secret.getBytes()).Build().parserClaimsJws(token).getBody();
    }
    public boolean isjwt_expired(String token){
        return extract_expiration(token).before(new Date());
    }
    public boolean validatetoken(String token, UserDetails userDetails){
        String username = extract_subject(token);
        return username.equals(userDetails.getUsername()) && isjwt_expired(token);
    }
    public String genneratetoken(UserDetails userDetails){
        return JwtSpec.builder().setsubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setexpiration(new Date(System.currentTimeMillis()) + 1000 * 60 * 60).signwith(SignatureAlgorithm.HS256,somethin_secret.getBytes()).Compact();
    }
}


