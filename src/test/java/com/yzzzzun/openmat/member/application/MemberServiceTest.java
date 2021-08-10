package com.yzzzzun.openmat.member.application;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yzzzzun.openmat.member.domain.Member;
import com.yzzzzun.openmat.member.domain.MemberRepository;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {

	@MockBean
	private MemberRepository memberRepository;

	@DisplayName("Member 추가 테스트")
	@Test
	void testAddMember() {
		MemberRequest memberRequest = new MemberRequest("test", "test", "test");
		Member expectedMember = Member.of(memberRequest);
		MemberService memberService = new MemberService(memberRepository);

		Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(expectedMember);

		MemberResponse actual = memberService.addMember(memberRequest);

		assertThat(actual.getLoginId()).isEqualTo(expectedMember.getLoginId());
		assertThat(actual.getName()).isEqualTo(expectedMember.getName());
	}
}