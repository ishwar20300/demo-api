package com.api.utility;

public interface Constants {

	String NOT_SUBMITTED = "Not Submitted";
	String PENDING_STATUS = "Pending";
	String APPROVE_STATUS = "Approved";
	String REJECT_STATUS = "Rejected";

	String CUST_NO_INIT = "GWICIN";
	String USER_UID = "UUID";

	// STATUS CODE
	int SUCCESS = 200;
	int RECORD_AVAILABLE = 300;
	int RECORD_NOT_AVAILABLE = 301;
	int SERVER_ERROR = 500;

	// STATUS MESSAGE
	String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	String RECORD_CREATED = "Record created successfully";
	String RECORD_NOT_FOUND = "Record not found..";
	String RECORD_FOUND = "Record found..";
	String RECORD_UPDATED = "Record updated successfully";
	String STATUS_CHANGED = "Status changed successfully";
	String RECORD_DELETED = "Record deleted successfully";
	String LIST_RETRIEVED = "List retrieved successfully";
	String LOGIN_SUCCESSFULL = "Successfully logged in";

	// OTHER MESSAGE
	String EMAIL_REGISTERED = "Email is already registered";
	String MOBILE_REGISTERED = "Mobile is already registered";
	String OLD_PASSWORD_WRONG = "Old password is wrong.";
	String PASSWORD_CHANGED = "Password changed successfully";
	String WRONG_PASSWORD = "You have entered wrong password.";
	String CHECK_USERNAME = "Username or password is incorrect";
	String ACCOUNT_IS_INACTIVE = "Your account is inactived. Please contact to admin.";
	String USER_NOT_FOUND = "Your account not found by provided id...";
	String OTP_SEND_SUCCESSFULLY = "OTP Send Successfully";
	String NOT_REQUESTED_FOR_OTP = "Not requested for OTP";
	String WRONG_OTP = "You have entered wrong OTP";
	String OTP_VERIFED = "OTP verified successfully";

}
