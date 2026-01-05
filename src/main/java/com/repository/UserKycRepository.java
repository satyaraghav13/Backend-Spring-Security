package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserKyc;
@Repository
public interface UserKycRepository extends JpaRepository<UserKyc, Long>{

}
