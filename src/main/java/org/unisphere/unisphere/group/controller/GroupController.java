package org.unisphere.unisphere.group.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unisphere.unisphere.group.service.GroupService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/groups")
public class GroupController {

	private final GroupService groupService;

	// 전체 단체 목록 조회
	// GET /api/v1/groups/all?page={page}&size={size}

	// 내가 속한 단체 목록 조회
	// GET /api/v1/groups/members/me?page={page}&size={size}

	// 특정 회원이 속한 단체 목록 조회
	// GET /api/v1/groups/members/{memberId}?page={page}&size={size} (pending)

	// 특정 단체의 홈피 정보 조회
	// GET /api/v1/groups/{groupId}/home-page

	// 특정 단체의 홈피 정보 편집
	// PUT /api/v1/groups/{groupId}/home-page

	// 특정 단체의 속한 회원 목록 조회
	// GET /api/v1/groups/{groupId}/members?page={page}&size={size} (pending)

	// 단체 생성 요청
	// POST /api/v1/groups

	// 단체 생성 승인
	// PATCH /api/v1/groups/{groupId}/accept (pending)

	// 단체 아바타 편집
	// PATCH /api/v1/groups/{groupId}/avatar (pending)

	// 단체 가입 요청
	// POST /api/v1/groups/{groupId}/register

	// TODO: 단체 생성 승인 뿐만 아니라 단체 가입 승인도 필요함.
	// 단체 가입 승인
	// PATCH /api/v1/groups/{groupId}/register/approve

	// 단체 탈퇴
	// DELETE /api/v1/groups/{groupId}/unregister (pending)

	// 특정 회원을 단체에 초대
	// POST /api/v1/groups/{groupId}/members/{memberId}/invite (pending)

	// 초대 코드로 단체 가입
	// POST /api/v1/groups/{groupId}/members/{memberId}/accept?code={code} (pending)

	// 특정 회원을 단체에서 추방
	// DELETE /api/v1/groups/{groupId}/members/{memberId}/kick (pending)
}
