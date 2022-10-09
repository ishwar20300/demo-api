package com.api.auth.service;

import com.api.auth.dto.AuthRequest;
import com.api.auth.dto.Response;
import com.api.auth.model.User;
import com.api.auth.model.UserDevices;

public interface UserService {

	public Response createNewUser(User user) throws Exception;

	public Response getUserDetail(String UUID);
	
	public Response deleteUser(String UUID);
	
	public Response updateProfile(User user);

	public Response changeUserStatus(String UUID);

	public Response changePassword(AuthRequest user);
	
	public Response saveLoggedDeviceInfo(UserDevices userDevices);
}
