package com.yzzzzun.openmat.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzzzzun.openmat.member.application.MemberService;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;

@RestController
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping(value = "/members")
	public ResponseEntity<MemberResponse> addMember(@RequestBody MemberRequest memberRequest) {
		MemberResponse memberResponse = this.memberService.addMember(memberRequest);
		return ResponseEntity.ok().body(memberResponse);
	}
}
