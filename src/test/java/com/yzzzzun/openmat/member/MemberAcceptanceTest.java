package com.yzzzzun.openmat.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.yzzzzun.openmat.AcceptanceTest;
import com.yzzzzun.openmat.member.dto.MemberRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class MemberAcceptanceTest extends AcceptanceTest {

	@DisplayName("Member를 생성합니다.")
	@Test
	void createMember() {

		MemberRequest memberRequest = new MemberRequest("test", "test", "test");
		ExtractableResponse<Response> createMemberResponse = Member추가를_요청합니다(memberRequest);

		assertThat(createMemberResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private ExtractableResponse<Response> Member추가를_요청합니다(MemberRequest memberRequest) {
		return RestAssured.given().log().all()
			.body(memberRequest)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when().post("/api/members")
			.then().log().all()
			.extract();
	}
}
