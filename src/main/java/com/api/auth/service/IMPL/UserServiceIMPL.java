package com.api.auth.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.api.auth.dao.UserCustomRepository;
import com.api.auth.dao.UserRepository;
import com.api.auth.dto.AuthRequest;
import com.api.auth.dto.Response;
import com.api.auth.model.User;
import com.api.auth.model.UserDevices;
import com.api.auth.service.UserService;
import com.api.utility.Constants;
import com.api.utility.RandomString;


@Service
public class UserServiceIMPL implements UserService {
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserCustomRepository userCustomRepository;

	@Override
	public Response createNewUser(User user) throws Exception {
		Response response = new Response();
		boolean isEmailExist = false;
		boolean isMobileExist = false;
		String password =  RandomString.randomString(6);
		System.out.println("USER PASSWORD : " + password);
		try {
			User isExistingUser = null;

			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				isExistingUser = userRepository.findByEmail(user.getEmail());
				if (isExistingUser != null)
					isEmailExist = true;
			}

			if (user.getMobile() != null && !user.getMobile().isEmpty() && !isEmailExist) {
				isExistingUser = userRepository.findByMobile(user.getMobile());
				if (isExistingUser != null)
					isMobileExist = true;

			}
			if (isExistingUser == null && !isEmailExist && !isMobileExist) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(password);
				user.setPassword(encodedPassword);
				user.setType("Admin_User");
				user.setUuid("UUID" + RandomString.randomString(8));
				user.setStatus(true);
				userRepository.save(user);

				// Send mail to user
				if (user.getEmail() != null) {
					String sendTo = user.getEmail();
					String subject = "Congratulations, your account is activated!";
					Context context = new Context();
					context.setVariable("password", password);
					context.setVariable("name", user.getFullName());
					context.setVariable("email", user.getEmail());
					context.setVariable("role", user.getRole());
				}

				response.setMessage(Constants.RECORD_CREATED);
				response.setStatus(Constants.SUCCESS);
			} else {
				if (isEmailExist)
					response.setMessage(Constants.EMAIL_REGISTERED);
				if (isMobileExist)
					response.setMessage(Constants.MOBILE_REGISTERED);
				response.setStatus(Constants.RECORD_AVAILABLE);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getUserDetail(String UUID) {
		Response response = new Response();
		try {

			User existingUser = userRepository.findByuuid(UUID);
			if (existingUser != null) {
				existingUser.setPassword(null);
				response.setResult(existingUser);
				response.setStatus(Constants.SUCCESS);
				response.setMessage(Constants.RECORD_UPDATED);
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
	public Response deleteUser(String UUID) {
		Response response = new Response();
		try {
			User existingUser = userRepository.findByuuid(UUID);
			if (existingUser != null) {
				userRepository.delete(existingUser);
				response.setStatus(Constants.SUCCESS);
				response.setMessage(Constants.RECORD_DELETED);
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
	public Response updateProfile(User user) {
		Response response = new Response();
		try {
			boolean isEmailExist = false;
			boolean isMobileExist = false;
			User existingUser = userRepository.findByuuid(user.getUuid());
			if (existingUser != null) {

				if (user.getEmail() != null && !user.getEmail().isEmpty()) {
					User checkDuplication = userRepository.findByEmail(user.getEmail());
					if (checkDuplication != null && !checkDuplication.getEmail().equals(existingUser.getEmail()))
						isEmailExist = true;
				}

				if (user.getMobile() != null && !user.getMobile().isEmpty() && !isEmailExist) {
					User checkDuplication = userRepository.findByMobile(user.getMobile());
					if (checkDuplication != null && !checkDuplication.getMobile().equals(existingUser.getMobile()))
						isMobileExist = true;

				}

				if (!isEmailExist && !isMobileExist) {
					existingUser.setDate_of_birth(user.getDate_of_birth());
					existingUser.setGender(user.getGender());
					existingUser.setRoleId(user.getRoleId());
					existingUser.setEmail(user.getEmail());
					existingUser.setFullName(user.getFullName());
					existingUser.setMobile(user.getMobile());
					existingUser.setRole(user.getRole());
					existingUser.setStatus(user.isStatus());
					userRepository.save(existingUser);
					response.setStatus(Constants.SUCCESS);
					response.setMessage(Constants.RECORD_UPDATED);
				} else {
					if (isEmailExist)
						response.setMessage(Constants.EMAIL_REGISTERED);
					if (isMobileExist)
						response.setMessage(Constants.MOBILE_REGISTERED);
					response.setStatus(Constants.RECORD_AVAILABLE);
				}
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
	public Response changeUserStatus(String UUID) {
		Response response = new Response();
		try {
			User existingUser = userRepository.findByuuid(UUID);
			if (existingUser != null) {
				if (existingUser.isStatus())
					existingUser.setStatus(false);
				else
					existingUser.setStatus(true);
				userRepository.save(existingUser);
				response.setStatus(Constants.SUCCESS);
				response.setMessage(Constants.STATUS_CHANGED);
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
	public Response changePassword(AuthRequest user) {
		Response response = new Response();
		try {
			User existingUser = userRepository.findByuuid(user.getUuid());
			if (existingUser != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				Boolean encodedPassword = passwordEncoder.matches(user.getOldPassword(), existingUser.getPassword());
				if (encodedPassword) {
					String newPassword = passwordEncoder.encode(user.getNewPassword());
					existingUser.setPassword(newPassword);
					userRepository.save(existingUser);
					response.setStatus(Constants.SUCCESS);
					response.setMessage(Constants.PASSWORD_CHANGED);
				} else {
					response.setStatus(Constants.RECORD_NOT_AVAILABLE);
					response.setMessage(Constants.OLD_PASSWORD_WRONG);
				}
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
	public Response saveLoggedDeviceInfo(UserDevices userDevices) {
		// TODO Auto-generated method stub
		return null;
	}



}
