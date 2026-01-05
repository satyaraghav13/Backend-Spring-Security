


package com.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.UserKyc;
import com.repository.UserKycRepository;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserKycController {

    @Autowired
    private UserKycRepository repo;

    @PostMapping("/reg")
    public String photoIDateDetails(
    		@RequestParam String name,
    		@RequestParam String aadhaar,
    		@RequestParam String pan,
    		@RequestParam String address,
    	
    		@RequestParam LocalDate dob,
    		@RequestParam MultipartFile photo,
    		@RequestParam MultipartFile aadhaarFile,
    		@RequestParam MultipartFile panFile
    ) throws Exception {

        UserKyc p = new UserKyc();
        p.setName(name);
        p.setAadhaar(aadhaar);
        p.setPan(pan);
        p.setAddress(address);
        
       
        p.setDob(dob);
        p.setPhoto(photo.getBytes()); 
        p.setAadhaarFile(aadhaarFile.getBytes());
        p.setPanFile(panFile.getBytes());// ✅ MOST IMPORTANT LINE
        
        System.out.println("Photo size = " + photo.getSize());
        System.out.println("Photo bytes length = " + photo.getBytes().length);


        repo.save(p);

        return "success"; // success.jsp
    }
    
    @GetMapping("/photo/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable int id) {

        byte[] image = repo.findById((long) id).get().getPhoto();

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(image);
    }

    }


