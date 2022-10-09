package com.api.auth.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.auth.model.Otp;

public interface AuthOtpRepository extends JpaRepository<Otp, UUID> {

	Otp findByuuid(String uuid);
	
	Otp findByMobile(String mobile);

	
}
