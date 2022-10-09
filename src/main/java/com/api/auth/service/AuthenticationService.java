package com.api.auth.service;

import com.api.auth.dto.AuthRequest;
import com.api.auth.dto.Response;
import com.api.auth.model.UserDevices;

public interface AuthenticationService {

	public Response login(AuthRequest authRequest);

	public Response changeForgotPassword(AuthRequest authRequest);

	public Response sendOTP(AuthRequest authRequest);

	public Response verifyOTP(AuthRequest authRequest);

	public Response sendOTPForMobileUser(AuthRequest authRequest);

	public Response loginWithOTPVerifyOTPMobileUser(AuthRequest authRequest) throws Exception;
	
	public void saveUserDeviceInfo(UserDevices userDevices);

}
