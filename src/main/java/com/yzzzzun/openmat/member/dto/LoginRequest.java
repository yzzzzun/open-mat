package com.yzzzzun.openmat.member.dto;

public class LoginRequest {
	private String loginId;
	private String password;

	protected LoginRequest() {
	}

	public LoginRequest(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}
	
}
