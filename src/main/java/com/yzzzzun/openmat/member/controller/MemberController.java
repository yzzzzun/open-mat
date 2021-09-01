package com.yzzzzun.openmat.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yzzzzun.openmat.member.application.MemberService;
import com.yzzzzun.openmat.member.dto.LoginRequest;
import com.yzzzzun.openmat.member.dto.LoginResponse;
import com.yzzzzun.openmat.member.dto.MemberRequest;
import com.yzzzzun.openmat.member.dto.MemberResponse;
import com.yzzzzun.openmat.utils.SessionUtils;

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

	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		LoginResponse loginResponse = memberService.login(loginRequest);
		SessionUtils.setLoginSession(session, loginRequest.getLoginId());

		return ResponseEntity.ok().body(loginResponse);
	}

	@GetMapping(value = "/members/me")
	public ResponseEntity<MemberResponse> getMyInfo(HttpSession session) {
		String loginId = SessionUtils.getLoginSession(session);
		MemberResponse memberResponse = this.memberService.findByLoginId(loginId);
		return ResponseEntity.ok().body(memberResponse);
	}

}
