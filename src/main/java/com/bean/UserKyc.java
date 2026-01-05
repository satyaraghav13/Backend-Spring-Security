package com.bean;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "User_kyc")
public class UserKyc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
   
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Column(name = "dob")

    private LocalDate dob;
    private String aadhaar;
    private String pan;
    private String address;
        
   
	@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[]  aadhaarFile;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] panFile;

    
    
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getAadhaarFile() {
		return aadhaarFile;
	}

	public void setAadhaarFile(byte[] aadhaarFile) {
		this.aadhaarFile = aadhaarFile;
	}

	public byte[] getPanFile() {
		return panFile;
	}

	public void setPanFile(byte[] panFile) {
		this.panFile = panFile;
	}

	}

	


