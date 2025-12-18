
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender; // Spring Boot automatically isko inject karega

    public void sendOtp(String toEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("your-email@gmail.com"); // Aapka Gmail address
            message.setTo(toEmail);
            message.setSubject("OneTouch Security Alert: Login Verification Required");

            
            // --- YOUR CODE MODIFIED START ---
            // Purana text simple tha, ab humne validity message bhi add kiya hai
            message.setText(
            	    "Dear User,\n\n" +
            	    "A login request was initiated for your OneTouch account.\n\n" +
            	    "Please enter the following One-Time Password (OTP) to authorize this action:\n\n" +
            	    "OTP: " + otp + "\n\n" +
            	    "This OTP is valid for 5 minutes and is required to ensure the security of your account.\n" +
            	    "Never share this OTP with anyone. OneTouch will never ask for your OTP or private credentials.\n\n" +
            	    "If you did not request this action, please ignore this message. No changes have been made to your account.\n\n" +
            	    "OneTouch Security\n\n" +
            	    "Automated message — do not reply."
            	);

            // --- YOUR CODE MODIFIED END ---

            mailSender.send(message);
            System.out.println("OTP successfully sent to " + toEmail);
        } catch (Exception e) {
            System.err.println("Error while sending email: " + e.getMessage());
        }
    }
}