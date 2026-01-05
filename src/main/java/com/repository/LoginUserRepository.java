package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer > {

	LoginUser findByEmail(String email);
	
	

	
	

}