package org.unisphere.unisphere.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unisphere.unisphere.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/members")
public class MemberController {

	private final MemberService memberService;

	// 내 아바타 정보 조회
	// GET api/v1/members/me/avatar

	// 내 아바타 편집
	// PATCH api/v1/members/me/avatar (pending)

	// 특정 회원 아바타 정보 조회
	// GET api/v1/members/{memberId}/avatar (pending)
}
