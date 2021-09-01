package com.yzzzzun.openmat.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yzzzzun.openmat.member.dto.MemberRequest;

class MemberTest {

	private MemberRequest request;

	@BeforeEach
	void setUp() {
		this.request = new MemberRequest("test", "test", "test");
	}

	@DisplayName("Member 생성 테스트 - 일반 유저")
	@Test
	void createMemberTest() {
		Member member = Member.of(this.request);

		assertThat(member.getMemberType()).isEqualTo(MemberType.USER);
		assertThat(member.getLoginId()).isEqualTo("test");
		assertThat(member.getPassword()).isEqualTo("test");
		assertThat(member.getName()).isEqualTo("test");
	}

	@DisplayName("Password 변경 테스트")
	@Test
	void testChangePassword() {
		Member member = Member.of(this.request);
		String changePassword = "changePassword";
		member.changePassword(changePassword);

		assertThat(member.getPassword()).isEqualTo(changePassword);
	}
}