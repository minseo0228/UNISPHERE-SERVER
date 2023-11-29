package org.unisphere.unisphere.article.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unisphere.unisphere.article.service.ArticleService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/articles")
public class ArticleController {

	private final ArticleService articleService;

	// 개인 이름으로 소식지 투고
	// POST /api/v1/articles/members/me

	// 단체 이름으로 소식지 투고
	// POST /api/v1/articles/groups/{groupId}

	// 소식지 투고 승인
	// PATCH /api/v1/articles/{articleId}/accept (pending)

	// 소식지 편집
	// PUT /api/v1/articles/{articleId} (pending)

	// 소식지 삭제
	// DELETE /api/v1/articles/{articleId} (pending)

	// 소식지 조회
	// GET /api/v1/articles/{articleId}

	// 소식지 목록 조회
	// GET /api/v1/articles/all?page={page}&size={size}

	// 내 소식지 목록 조회
	// GET /api/v1/articles/members/me?page={page}&size={size} (pending)

	// 특정 회원의 소식지 목록 조회
	// GET /api/v1/articles/members/{memberId}?page={page}&size={size} (pending)

	// 특정 단체의 소식지 목록 조회
	// GET /api/v1/articles/groups/{groupId}?page={page}&size={size} (pending)

	// 관심 소식지 등록
	// PUT /api/v1/articles/{articleId}/like (pending)

	// 관심 소식지 해제
	// DELETE /api/v1/articles/{articleId}/like (pending)
}
