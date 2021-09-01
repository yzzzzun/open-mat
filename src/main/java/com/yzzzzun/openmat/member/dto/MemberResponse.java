package com.yzzzzun.openmat.member.dto;

import com.yzzzzun.openmat.member.domain.Member;

public class MemberResponse {
	private Long id;
	private String loginId;
	private String name;

	protected MemberResponse() {
	}

	public MemberResponse(Long id, String loginId, String name) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
	}

	public static MemberResponse ofMember(Member member) {
		return new MemberResponse(member.getId(), member.getLoginId(), member.getName());
	}

	public Long getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getName() {
		return name;
	}
}
