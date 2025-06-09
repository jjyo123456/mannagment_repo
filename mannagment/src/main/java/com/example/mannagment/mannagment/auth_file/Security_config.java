package main.java.com.example.mannagment.mannagment.auth_file;




import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class Security_config {

@Autowired
public JwtFilter jwtfilter;

@Autowired 
public UserDetailsService userdetailsservice;

@Bean
public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
    http.csrf().disable()
    .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/signup", "/auth/login/{name}").permitAll().anyRequest().authenticated())
    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

   return http.build();
}
    
}
