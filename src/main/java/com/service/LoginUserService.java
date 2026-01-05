package com.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bean.LoginUser;
import com.repository.LoginUserRepository;

@Service
public class LoginUserService {
	
    private final LoginUserRepository repository;
    private final PasswordEncoder encoder;
	
    // FIXED: static keyword memory maintain karne ke liye zaroori hai
    private static Map<String, String> tempOtpStore = new HashMap<>();
	
    public LoginUserService(LoginUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
	
    public LoginUser save(LoginUser loginUser) {
        loginUser.setPassword(encoder.encode(loginUser.getPassword()));
        if(loginUser.getRole() != null) {
            loginUser.setRole(loginUser.getRole().toUpperCase());
        } else {
            loginUser.setRole("USER");
        }
        return repository.save(loginUser);
    }

    public LoginUser findByEmail(String email) {
        return repository.findByEmail(email != null ? email.trim().toLowerCase() : null);
    }

    // --- UPDATED: Email trim aur lowercase logic ---
    public String generateTempOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        tempOtpStore.put(email.trim().toLowerCase(), otp); 
        System.out.println("OTP stored for " + email + ": " + otp); // Debug log
        return otp;
    }

    public boolean verifyTempOtp(String email, String enteredOtp) {
        String key = email.trim().toLowerCase();
        if (tempOtpStore.containsKey(key) && tempOtpStore.get(key).equals(enteredOtp)) {
            tempOtpStore.remove(key); 
            return true;
        }
        return false;
    }

    public String generateAndSaveOtp(String email) {
        LoginUser user = repository.findByEmail(email.trim().toLowerCase());
        if (user != null) {
            String otp = String.format("%06d", new Random().nextInt(999999));
            user.setOtp(otp);
            user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
            repository.save(user);
            return otp;
        }
        return null;
    }

    public boolean verifyOtp(String email, String enteredOtp) {
        LoginUser user = repository.findByEmail(email.trim().toLowerCase());
        if (user != null && user.getOtp() != null && user.getOtp().equals(enteredOtp)) {
            if (user.getOtpExpiry().isAfter(LocalDateTime.now())) {
                user.setOtp(null);
                user.setOtpExpiry(null);
                repository.save(user);
                return true;
            }
        }
        return false;
    }
    
    public PasswordEncoder getEncoder() { return this.encoder; }
}