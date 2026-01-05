package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class CorsConfig {


    // HIGHLIGHT: Define BCryptPasswordEncoder as a Bean for hashing passwords
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
	
    // Example InMemory users (not related to the DB users)
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {

        UserDetails u1 = User.builder()
                .username("sudhir")
                .password(passwordEncoder.encode("12")) 
                .roles("ADMIN")
                .build();
        System.err.println("Encoded password for 'sudhir': " + passwordEncoder.encode("12") ); 

        UserDetails u2 = User.builder()
                .username("rahul")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(u1, u2);
    }
	 
    
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/Save", "/loginUser", "/login", "/SignUp", "/verify-otp","/reg" ).permitAll()
//                		.requestMatchers("/kyc/**").authenticated()
                     
                        .anyRequest()
                        .authenticated() 
                )
               
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}