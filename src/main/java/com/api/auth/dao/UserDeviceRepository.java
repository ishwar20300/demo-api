package com.api.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.auth.model.UserDevices;

public interface UserDeviceRepository extends JpaRepository<UserDevices, Long> {

	UserDevices findByfcm(String fcm);
}
