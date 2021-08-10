package com.yzzzzun.openmat.member.dto;

public class MemberRequest {

	private String loginId;
	private String name;
	private String password;

	protected MemberRequest() {
	}

	public MemberRequest(String loginId, String name, String password) {
		this.loginId = loginId;
		this.name = name;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
