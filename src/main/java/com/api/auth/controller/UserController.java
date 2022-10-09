package com.api.auth.controller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.auth.dto.AuthRequest;
import com.api.auth.dto.Response;
import com.api.auth.model.User;
import com.api.auth.service.UserService;
import com.api.utility.Constants;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/***
	 * PI Use to create new user by admin panel
	 ******/

	@PostMapping("/rest/user/add")
	public Response saveUser(@RequestBody User user) {
		Response response = new Response();
		try {
			response = userService.createNewUser(user);
		} catch (Exception e) {
			logger.error("ERROR In /admin/user/add API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * GET USER DETAIL
	 ******/

	@GetMapping("/rest/user/{uuid}")
	public Response getUserDetail(@PathVariable String uuid) {
		Response response = new Response();
		try {
			response = userService.getUserDetail(uuid);
		} catch (Exception e) {
			logger.error("ERROR In /user/{uuid} API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	
	/***
	 * DELETE ADMIN USER RECORD 
	 ******/

	@DeleteMapping("/rest/user/delete/{uuid}")
	public Response deleteUser(@PathVariable String uuid) {
		Response response = new Response();
		try {
			response = userService.deleteUser(uuid);
		} catch (Exception e) {
			logger.error("ERROR In /rest/user/delete/{uuid} API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	/***
	 * UPDATE USER PROFILE
	 ******/

	@PutMapping("/rest/user/update")
	public Response updateUser(@RequestBody User user) {
		Response response = new Response();
		try {
			response = userService.updateProfile(user);
		} catch (Exception e) {
			logger.error("ERROR In /user/update API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * GET USER DETAIL
	 ******/

	@GetMapping("/rest/change-user-status/{uuid}")
	public Response changeUserStatus(@PathVariable String uuid) {
		Response response = new Response();
		try {
			response = userService.changeUserStatus(uuid);
		} catch (Exception e) {
			logger.error("ERROR In Change User Status API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/***
	 * CHANGED PASSWORD
	 ******/
	@PostMapping("/rest/user/change-password")
	public Response changePassword(@RequestBody AuthRequest user) {
		Response response = new Response();
		try {
			response = userService.changePassword(user);
		} catch (Exception e) {
			logger.error("ERROR In /user/change-password API : " + e);
			response.setStatus(Constants.SERVER_ERROR);
			response.setMessage(Constants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


}
