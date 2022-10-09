package com.api.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import  com.api.auth.dto.AuthRequest;
import  com.api.auth.dto.Response;
import  com.api.auth.model.UserDevices;
import  com.api.auth.service.AuthenticationService;
import  com.api.utility.Constants;
import com.twilio.exception.ApiException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class AuthenticationController {

	Logger logger = LogManager.getLogger(AuthenticationController.class);

	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	/***
	 * DO LOGIN
	 ******/
	@PostMapping("/login")
	public Response changePassword(@RequestBody AuthRequest authRequest) {
		Response response = new Response();
		try {
			response = authenticationService.login(authRequest);
		} catch (Exception e) {
			logger.error("ERROR In Login API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * SEND/RESEND OTP
	 ******/
	@PostMapping("/forgot-password/send-otp")
	public Response sendOTP(@RequestBody AuthRequest user) {
		Response response = new Response();
		try {
			response = authenticationService.sendOTP(user);
		} catch (Exception e) {
			logger.error("ERROR In send OTP API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * CHANGE FORGOT PASSWORD PASSWORD
	 ******/
	@PostMapping("/forgot-password/change-password")
	public Response changeForgotPassword(@RequestBody AuthRequest user) {
		Response response = new Response();
		try {
			response = authenticationService.changeForgotPassword(user);
		} catch (Exception e) {
			logger.error("ERROR In /forgot-password/change-password API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * CHANGE FORGOT PASSWORD OTP VERIFICATION
	 ******/
	@PostMapping("/forgot-password/otp-verification")
	public Response verifyOTP(@RequestBody AuthRequest user) {
		Response response = new Response();
		try {
			response = authenticationService.verifyOTP(user);
		} catch (Exception e) {
			logger.error("ERROR In /forgot-password/otp-verification API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * LOGIN WITH OTP SEND OTP For Mobile app user
	 ******/
	@PostMapping("/otp")
	public Response sendOTPForMobileUser(@RequestParam("phoneNumber") String phoneNumber) {
		Response response = new Response();
		try {
			AuthRequest authRequest = new AuthRequest();
			authRequest.setMobile(phoneNumber);
			response = authenticationService.sendOTPForMobileUser(authRequest);
		} catch (ApiException e) {
			response.setStatus(e.getStatusCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("ERROR In send OTP Fo rMobile User : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * LOGIN WITH OTP VERIFY OTP For Mobile app user
	 ******/
	@PostMapping("/mobile/otp")
	public Response loginWithOTPVerifyOTPMobileUser(@RequestBody AuthRequest authRequest) {
		Response response = new Response();
		try {
			response = authenticationService.loginWithOTPVerifyOTPMobileUser(authRequest);
		} catch (Exception e) {
			logger.error("ERROR In verify otp for mobile app user : loginWithOTPVerifyOTPMobileUser : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * SAVE USER LOGGED DEVICE INFORMATION
	 ******/
	@PostMapping("/device-information")
	public void saveUserDeviceInfo(@RequestBody UserDevices userDevices) {
		try {
			authenticationService.saveUserDeviceInfo(userDevices);
		} catch (Exception e) {
			logger.error("ERROR In /device-information API : " + e);
		}
	}

}
