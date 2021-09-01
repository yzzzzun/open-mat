package com.yzzzzun.openmat.member.application;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yzzzzun.openmat.member.domain.Member;
import com.yzzzzun.openmat.member.domain.MemberRepository;
import com.yzzzzun.openmat.member.domain.MemberType;
import com.yzzzzun.openmat.member.dto.LoginRequest;
import com.yzzzzun.openmat.member.dto.LoginResponse;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private MemberService memberService;

	private LoginRequest testLoginRequest;
	private Member testMember;

	@BeforeEach
	void setUp() {
		testLoginRequest = new LoginRequest("test", "test");
		testMember = new Member("test", "test", "test", MemberType.USER);
	}

	@DisplayName("Member 추가 테스트")
	@Test
	void testAddMember() {
		MemberRequest memberRequest = new MemberRequest("test", "test", "test");
		Member expectedMember = Member.of(memberRequest);

		Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(expectedMember);

		MemberResponse actual = memberService.addMember(memberRequest);

		assertThat(actual.getLoginId()).isEqualTo(expectedMember.getLoginId());
		assertThat(actual.getName()).isEqualTo(expectedMember.getName());
	}

	@DisplayName("Login success 테스트")
	@Test
	void testLoginSuccess() {
		Mockito.when(memberRepository.findOptionalByLoginId(testLoginRequest.getLoginId()))
			.thenReturn(Optional.of(testMember));
		Mockito.when(
			passwordEncoder.matches(Mockito.eq(testLoginRequest.getPassword()), Mockito.eq(testMember.getPassword())))
			.thenReturn(true);

		LoginResponse result = memberService.login(testLoginRequest);

		assertThat(result.getLoginId()).isEqualTo(testMember.getLoginId());
		assertThat(result.getName()).isEqualTo(testMember.getName());
		assertThat(result.getMemberType()).isEqualTo(testMember.getMemberType());
	}

	@DisplayName("Login failure 테스트 - 가입되지 않은 사용자")
	@Test
	void testLoginNotfoundLoginId() {
		Mockito.when(memberRepository.findOptionalByLoginId(testLoginRequest.getLoginId()))
			.thenReturn(Optional.empty());

		Assertions.assertThatThrownBy(() -> {
			memberService.login(testLoginRequest);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("가입된 사용자가 아닙니다.");
	}

	@DisplayName("Login failure 테스트 - 비밀번호 불일치")
	@Test
	void testLoginNotMatchesPassword() {
		Mockito.when(memberRepository.findOptionalByLoginId(testLoginRequest.getLoginId()))
			.thenReturn(Optional.of(testMember));

		testMember.changePassword("changed");

		Assertions.assertThatThrownBy(() -> {
			memberService.login(testLoginRequest);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("잘못된 로그인 정보입니다.");
	}
}