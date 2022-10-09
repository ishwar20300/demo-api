package com.api.auth.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.auth.dto.AuthRequest;
import com.api.auth.dto.AuthResponse;
import com.api.auth.dto.OtpDto;
import com.api.auth.dto.Response;
import com.api.auth.model.User;
import com.api.auth.model.UserDevices;
import com.api.auth.dao.AuthOtpRepository;
import com.api.auth.dao.UserDeviceRepository;
import com.api.auth.dao.UserRepository;
import com.api.auth.service.AuthenticationService;
import com.api.utility.Constants;
import com.api.utility.RandomString;


@Service
public class AuthenticationServiceIMPL implements AuthenticationService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthOtpRepository authOtpRepository;


	@Autowired
	UserDeviceRepository userDeviceRepository;

	@Override
	public Response login(AuthRequest user) {
		Response response = new Response();
		User isExistingUser = null;
		try {

			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				isExistingUser = userRepository.findByEmail(user.getEmail());
			}

			if (user.getMobile() != null && !user.getMobile().isEmpty()) {
				isExistingUser = userRepository.findByMobile(user.getMobile());
			}
			if (isExistingUser != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				Boolean decodedPassword = passwordEncoder.matches(user.getPassword(), isExistingUser.getPassword());
				if (decodedPassword) {

					if (isExistingUser.isStatus()) {

						// SAVE DEVICE INFO
						if (user.getFcm() != null && !user.getFcm().isEmpty()) {
							UserDevices userDevice = userDeviceRepository.findByfcm(user.getFcm());
							if (userDevice == null)
								userDevice = new UserDevices();

							userDevice.setFcm(user.getFcm());
							userDevice.setManufacturer(user.getManufacturer());
							userDevice.setModel(user.getModel());
							userDevice.setPlatform(user.getPlatform());
							userDevice.setUniqueDeviceId(user.getUniqueDeviceId());
							userDevice.setVersion(user.getVersion());
							userDevice.setUuid(isExistingUser.getUuid());
							userDeviceRepository.save(userDevice);
						}

						AuthResponse authResponse = new AuthResponse();

						authResponse.setUserId(isExistingUser.getUserId());
						authResponse.setEmail(isExistingUser.getEmail());
						authResponse.setFullName(isExistingUser.getFullName());
						authResponse.setMobile(isExistingUser.getMobile());
						authResponse.setRole(isExistingUser.getRole());
						authResponse.setStatus(isExistingUser.isStatus());
						authResponse.setUuid(isExistingUser.getUuid());
						authResponse.setType(isExistingUser.getType());
						response.setResult(authResponse);
						response.setStatus(Constants.SUCCESS);
						response.setMessage(Constants.LOGIN_SUCCESSFULL);
					} else {
						response.setStatus(Constants.RECORD_NOT_AVAILABLE);
						response.setMessage(Constants.ACCOUNT_IS_INACTIVE);
					}

				} else {
					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
					response.setMessage(Constants.WRONG_PASSWORD);
				}
			} else {
				response.setMessage(Constants.CHECK_USERNAME);
				response.setStatus(Constants.RECORD_AVAILABLE);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeForgotPassword(AuthRequest user) {
		Response response = new Response();
		User isExistingUser = null;
		try {
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				isExistingUser = userRepository.findByEmail(user.getEmail());
			} else if (user.getMobile() != null && !user.getMobile().isEmpty()) {
				isExistingUser = userRepository.findByMobile(user.getMobile());
			} else if (user.getUuid() != null && !user.getUuid().isEmpty()) {
				isExistingUser = userRepository.findByuuid(user.getUuid());
			}
			if (isExistingUser != null) {

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				isExistingUser.setPassword(encodedPassword);

				userRepository.save(isExistingUser);

				response.setStatus(Constants.SUCCESS);
				response.setMessage(Constants.PASSWORD_CHANGED);

			} else {
				response.setStatus(Constants.RECORD_NOT_AVAILABLE);
				response.setMessage(Constants.RECORD_NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response sendOTP(AuthRequest authRequest) {
		Response response = new Response();
		User isExistingUser = null;
		try {

			if (authRequest.getEmail() != null && !authRequest.getEmail().isEmpty()) {
				isExistingUser = userRepository.findByEmail(authRequest.getEmail());

				if (isExistingUser != null) {


				
				
					response.setStatus(Constants.SUCCESS);
					response.setMessage(Constants.OTP_SEND_SUCCESSFULLY);

				} else {
					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
					response.setMessage(Constants.USER_NOT_FOUND);
				}

			} else if (authRequest.getMobile() != null && !authRequest.getMobile().isEmpty()) {
				isExistingUser = userRepository.findByMobile(authRequest.getMobile());
				if (isExistingUser != null) {
				
					OtpDto otpBO = new OtpDto();
					otpBO.setUuid(isExistingUser.getUuid());
					response.setResult(otpBO);
					response.setStatus(Constants.SUCCESS);
					response.setMessage(Constants.OTP_SEND_SUCCESSFULLY);

				} else {
					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
					response.setMessage(Constants.USER_NOT_FOUND);
				}
			} else {
				response.setStatus(Constants.RECORD_NOT_AVAILABLE);
				response.setMessage(Constants.USER_NOT_FOUND);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response verifyOTP(AuthRequest authRequest) {
		Response response = new Response();
		try {
//		//	Otp authOTP = authOtpRepository.findByuuid(authRequest.getUuid());
//			if (authOTP != null) {
//				if (authOTP.getOtp().equalsIgnoreCase(authRequest.getOtp())) {
//					response.setStatus(Constants.SUCCESS);
//					response.setMessage(Constants.OTP_VERIFED);
//				} else {
//					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//					response.setMessage(Constants.WRONG_OTP);
//				}
//			} else {
//				response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//				response.setMessage(Constants.NOT_REQUESTED_FOR_OTP);
//			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response sendOTPForMobileUser(AuthRequest authRequest) {
		Response response = new Response();
		try {

//			if (authRequest.getMobile() != null && !authRequest.getMobile().isEmpty()) {
//			
//				User user = userRepository.findByMobile(authRequest.getMobile());
//			
//				Otp otpData = authOtpRepository.findByMobile(authRequest.getMobile());
//				response.setStatus(Constants.SUCCESS);
//				response.setMessage(Constants.OTP_SEND_SUCCESSFULLY);
//			} else {
//				response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//				response.setMessage("Please provide mobile number.. ");
//			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response loginWithOTPVerifyOTPMobileUser(AuthRequest authRequest) throws Exception {
		Response response = new Response();
		try {
//			AuthResponse authResponse = new AuthResponse();
//			if (authRequest.getMobile() != null && !authRequest.getMobile().isEmpty() && authRequest.getOtp() != null
//					&& !authRequest.getOtp().isEmpty()) {
//				//Otp otpData = authOtpRepository.findByMobile(authRequest.getMobile());
//				if (otpData != null) {
//					if (otpData.getOtp().equals(authRequest.getOtp())) {
//
//						// START : SAVE USER IN AUTH USER TABLE
//						User authUser = userRepository.findByMobile(authRequest.getMobile());
//						if (authUser == null) {
//							authUser = new User();
//							authUser.setMobile(authRequest.getMobile());
//							authUser.setUuid(otpData.getUuid());
//							authUser.setStatus(true);
//							authUser.setCustomerNo(Constants.CUST_NO_INIT + RandomString.randomString(8));
//							authUser.setType("End_User");
//
//							userRepository.save(authUser);
//
//			
//						}
//				
//
//						// SAVE DEVICE INFO
//						if (authRequest.getFcm() != null && !authRequest.getFcm().isEmpty()) {
//							UserDevices userDevice = userDeviceRepository.findByfcm(authRequest.getFcm());
//							if (userDevice == null)
//								userDevice = new UserDevices();
//
//							userDevice.setFcm(authRequest.getFcm());
//							userDevice.setManufacturer(authRequest.getManufacturer());
//							userDevice.setModel(authRequest.getModel());
//							userDevice.setPlatform(authRequest.getPlatform());
//							userDevice.setUniqueDeviceId(authRequest.getUniqueDeviceId());
//							userDevice.setVersion(authRequest.getVersion());
//							userDevice.setUuid(authUser.getUuid());
//							userDeviceRepository.save(userDevice);
//						}
//
//						authResponse.setUserId(authUser.getUserId());
//						authResponse.setUuid(authUser.getUuid());
//						authResponse.setMobile(authUser.getMobile());
//						authResponse.setEmail(authUser.getEmail());
//						authResponse.setFullName(authUser.getFullName());
//						authResponse.setType(authUser.getType());
//						authResponse.setStatus(authUser.isStatus());
//				
//						response.setResult(authResponse);
//						response.setStatus(Constants.SUCCESS);
//						response.setMessage(Constants.OTP_VERIFED);
//					} else {
//						response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//						response.setMessage(Constants.WRONG_OTP);
//					}
//				} else {
//					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//					response.setMessage(Constants.NOT_REQUESTED_FOR_OTP);
//				}
//			} else {
//				response.setStatus(Constants.RECORD_NOT_AVAILABLE);
//				response.setMessage("Please provide Mobile No & OTP ");
//			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void saveUserDeviceInfo(UserDevices requestUserDevices) {
		UserDevices userDevice = userDeviceRepository.findByfcm(requestUserDevices.getFcm());
		if (userDevice == null)
			userDevice = new UserDevices();

		userDevice.setFcm(requestUserDevices.getFcm());
		userDevice.setManufacturer(requestUserDevices.getManufacturer());
		userDevice.setModel(requestUserDevices.getModel());
		userDevice.setPlatform(requestUserDevices.getPlatform());
		userDevice.setUniqueDeviceId(requestUserDevices.getUniqueDeviceId());
		userDevice.setVersion(requestUserDevices.getVersion());
		userDevice.setUuid(requestUserDevices.getUuid());
		userDeviceRepository.save(userDevice);

	}

}
