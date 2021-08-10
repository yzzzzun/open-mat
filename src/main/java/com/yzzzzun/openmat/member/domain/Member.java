package com.yzzzzun.openmat.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yzzzzun.openmat.common.BaseEntity;
import com.yzzzzun.openmat.member.dto.MemberRequest;

@Entity
@Table(name = "member")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login_id", nullable = false)
	private String loginId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(value = EnumType.STRING)
	private MemberType memberType;

	protected Member() {
	}

	public Member(String loginId, String password, String name, MemberType memberType) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.memberType = memberType;
	}

	public static Member of(MemberRequest memberRequest) {
		return new Member(memberRequest.getLoginId(), memberRequest.getPassword(), memberRequest.getName(),
			MemberType.USER);
	}

	public Long getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public MemberType getMemberType() {
		return memberType;
	}
}
