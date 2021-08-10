package com.yzzzzun.openmat.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yzzzzun.openmat.member.dto.MemberRequest;

class MemberTest {

	@DisplayName("Member 생성 테스트 - 일반 유저")
	@Test
	void createMemberTest() {

		MemberRequest request = new MemberRequest("test", "test", "test");
		Member member = Member.of(request);

		assertThat(member.getMemberType()).isEqualTo(MemberType.USER);
		assertThat(member.getLoginId()).isEqualTo("test");
		assertThat(member.getPassword()).isEqualTo("test");
		assertThat(member.getName()).isEqualTo("test");
	}
}