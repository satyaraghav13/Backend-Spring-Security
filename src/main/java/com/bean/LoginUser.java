package com.bean;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LocalDateTime getLastOtpSent() {
		return lastOtpSent;
	}
	public void setLastOtpSent(LocalDateTime lastOtpSent) {
		this.lastOtpSent = lastOtpSent;
	}
	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	public LocalDateTime getLockUntil() {
		return lockUntil;
	}
	public void setLockUntil(LocalDateTime lockUntil) {
		this.lockUntil = lockUntil;
	}
	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}
	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	private String email;
    private String phone;
    
    private String password;
    private String role="User";
    
    private String otp;
    
    private LocalDateTime lastOtpSent;    // Pichla OTP kab bheja
    private int failedAttempts = 0;       // Kitni baar galat OTP dala
    private LocalDateTime lockUntil;      // Kab tak account lock hai
    private LocalDateTime otpExpiry;      // OTP kab khatam hoga
    
    
    
}