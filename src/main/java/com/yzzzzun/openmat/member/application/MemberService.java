package com.yzzzzun.openmat.member.application;

import org.springframework.stereotype.Service;

import com.yzzzzun.openmat.member.domain.Member;
import com.yzzzzun.openmat.member.domain.MemberRepository;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponse addMember(MemberRequest memberRequest) {
		Member member = Member.of(memberRequest);
		memberRepository.save(member);
		return new MemberResponse(member.getId(), member.getLoginId(), member.getName());
	}
}
