package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bean.LoginUser;
import com.service.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginUserController {

    @Autowired
    private LoginUserService userService;
    
    @Autowired
    private EmailService emailService; 

    @PostMapping("/Save")
    public ResponseEntity<String> saveUser(@RequestBody LoginUser user) {
        if (user.getEmail() == null || !user.getEmail().toLowerCase().endsWith("@gmail.com")) {
            return ResponseEntity.status(400).body("Only Gmail addresses are allowed");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(400).body("Email already registered");
        }
        
        String otp = userService.generateTempOtp(user.getEmail());
        emailService.sendOtp(user.getEmail(), otp);
        
        return ResponseEntity.ok("OTP sent to your Gmail.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody LoginUser request) {
        String email = request.getEmail().trim().toLowerCase();
        String otp = request.getOtp();

        // Registration Case: Jab frontend se password bhi aa raha ho
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            if (userService.verifyTempOtp(email, otp)) {
                userService.save(request); 
                return ResponseEntity.ok("Registration Successful!");
            }
        } 
        // Login Case
        else {
            if (userService.verifyOtp(email, otp)) {
                return ResponseEntity.ok("Login Successful!");
            }
        }
        
        return ResponseEntity.status(401).body("Invalid or Expired OTP!");
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody LoginUser login) {
        LoginUser user = userService.findByEmail(login.getEmail());

        if (user == null) return ResponseEntity.status(401).body("Email not found!");
        
        if (!userService.getEncoder().matches(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Wrong password!");
        }

        String otp = userService.generateAndSaveOtp(login.getEmail());
        if (otp != null) {
            emailService.sendOtp(login.getEmail(), otp);
            return ResponseEntity.ok("OTP sent to your gmail.");
        }
        return ResponseEntity.status(500).body("Error generating OTP");
    }
}