package com.yzzzzun.openmat.member.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yzzzzun.openmat.member.domain.Member;
import com.yzzzzun.openmat.member.domain.MemberRepository;
import com.yzzzzun.openmat.member.dto.LoginRequest;
import com.yzzzzun.openmat.member.dto.LoginResponse;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;

@Service
public class MemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger("file");

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public MemberResponse addMember(MemberRequest memberRequest) {
		Member member = Member.of(memberRequest);
		String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
		member.changePassword(encodedPassword);

		memberRepository.save(member);
		LOGGER.info("회원 등록 성공 : {}", member);
		return new MemberResponse(member.getId(), member.getLoginId(), member.getName());
	}

	public LoginResponse login(LoginRequest loginRequest) {
		Member member = memberRepository.findOptionalByLoginId(loginRequest.getLoginId())
			.orElseThrow(() -> new IllegalArgumentException("가입된 사용자가 아닙니다."));

		if (!matchesPassword(loginRequest, member)) {
			throw new IllegalArgumentException("잘못된 로그인 정보입니다.");
		}
		LOGGER.info("로그인 성공 : {}", member);
		return LoginResponse.ofMember(member);
	}

	public MemberResponse findByLoginId(String loginId) {
		Member member = memberRepository.findOptionalByLoginId(loginId)
			.orElseThrow(() -> new IllegalArgumentException("로그인된 유저 정보를 찾을 수 없습니다."));

		return MemberResponse.ofMember(member);
	}

	private boolean matchesPassword(LoginRequest loginRequest, Member member) {
		return passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
	}
}
