package com.api.auth.dto;

public class Response {

	private Integer status;

	private String message;
	
	private Object result;
	
	private Long listCount;
	
	private String otp;



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Long getListCount() {
		return listCount;
	}

	public void setListCount(Long listCount) {
		this.listCount = listCount;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	
}
