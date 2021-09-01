package com.yzzzzun.openmat.member.dto;

import com.yzzzzun.openmat.member.domain.Member;
import com.yzzzzun.openmat.member.domain.MemberType;

public class LoginResponse {
	private String loginId;
	private String name;
	private MemberType memberType;

	protected LoginResponse() {
	}

	public static LoginResponse ofMember(Member member) {
		return new LoginResponse(member.getLoginId(), member.getName(), member.getMemberType());
	}

	public LoginResponse(String loginId, String name, MemberType memberType) {
		this.loginId = loginId;
		this.name = name;
		this.memberType = memberType;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getName() {
		return name;
	}

	public MemberType getMemberType() {
		return memberType;
	}
}
