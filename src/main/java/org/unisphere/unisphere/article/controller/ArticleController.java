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
	@Operation(summary = "개인 이름으로 소식지 투고", description = "개인 이름으로 소식지를 투고합니다.")
	@ApiResponse(responseCode = "201", description = "Created")
	@PostMapping(value = "/members/me")
	public ResponseEntity<ArticleResponseDto> submitArticleForSelf(@RequestBody ArticleRequestDto articleRequestDto) {
		ArticleResponseDto createdArticle = articleService.submitArticleForSelf(articleRequestDto);
		return ResponseEntity.status(201).body(createdArticle);
	}

	// 단체 이름으로 소식지 투고
	// POST /api/v1/articles/groups/{groupId}
	@Operation(summary = "단체 이름으로 소식지 투고", description = "단체이름으로 소식지를 투고합니다.")
	@ApiResponse(responseCode = "201", description = "Created")
	@PostMapping(value = "/groups/{groupId}")
	public ResponseEntity<ArticleResponsesDto> submintArticleForGroup(
			@PathVariable Long groupId,
			@RequestBody ArticleRequestDto articleRequestDto) {
		ArticleResponseDto createdArticle = articleService.submitArticleForGroup(groupId, articleRequestDto);
		return ResponseEntity.status(201).body(createdArticle);
	}


	// 소식지 투고 승인
	// PATCH /api/v1/articles/{articleId}/accept (pending)
	@Operation(summary = "소식지 투고 승인", description = "소식지 투고를 승인합니다. ")
	@ApiResponse(responseCode = "200", description = "ok")
	@PatchMapping(value = "/{articleId}/accept")
	public ResponseEntity<Void> acceptArticleSubmission(@PathVariable Long articleId) {
		articleService.acceptArticleSubmission(articleId);
		return ResponseEntity.ok().build();
	}
	// 소식지 편집
	// PUT /api/v1/articles/{articleId} (pending)
	@Operation(summary = "소식지 편집", description = "소식지를 편집합니다. ")
	@ApiResponse(responseCode = "200", description = "ok")
	@PutMapping(value = "/{articleId}")
	public ResponseEntity<ArticleResponseDto> editArticle(
			@PathVariable Long articleId,
			@RequestBody ArticleRequestDto editedArticleDto) {
		ArticleResponseDto editedArticle = articleService.editArticle(articleId, editedArticleDto);
		return ResponseEntity.ok(editedArticle);
	}

	// 소식지 삭제
	// DELETE /api/v1/articles/{articleId} (pending)
	@Operation(summary = "소식지 삭제", description = "소식지를 삭제합니다. ")
	@ApiResponse(responseCode = "204", description = "no content")
	@DeleteMapping(value = "/{articleId}")
	public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
		articleService.deleteArticle(ArticleId);
		return ResponseEntity.noContent().build();
	}
	// 소식지 조회
	// GET /api/v1/articles/{articleId}
	@Operation(summary = "소식지 조회", description = "소식지를 조회합니다.")
	@ApiResponse(value = {
			@ApiResponse(responseCode = "200", description = "ok")
	})
	@GetMapping(value = "/{articleId}")
	public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long articleId) {
		ArticleResponseDto article = articleService.getArticleById(articleId);
		return ResponseEntity.ok(article);
	}

	// 소식지 목록 조회
	// GET /api/v1/articles/all?page={page}&size={size}
	@Operation(summary = "소식지 목록 조회", description = "모든 소식지를 조회합니다.")
	@ApiResponse(value = {
			@ApiResponse(responseCode = "200", description = "ok")
	})
	@GetMapping(value = "/all")
	public ResponseEntity<List<ArticleResponseDto>> getAllArticles(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		List<ArticleResponseDto> articles = articleService.getAllArticles(page, size);
		return ResponseEntity.ok(articles);
	}

	// 내 소식지 목록 조회
	// GET /api/v1/articles/members/me?page={page}&size={size} (pending)
	@Operation(summary = "내 소식지 목록 조회", description = "현재 사용자의 소식지 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping(value = "/members/me")
	public ResponseEntity<List<ArticleResponseDto>> getMyArticles(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		List<ArticleResponseDto> myArticles = articleService.getMyArticles(page, size);
		return ResponseEntity.ok(myArticles);
	}

	// 특정 회원의 소식지 목록 조회
	// GET /api/v1/articles/members/{memberId}?page={page}&size={size} (pending)
	@Operation(summary = "특정 회원의 소식지 목록 조회", description = "특정 회원의 소식지 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping(value = "/members/{memberId}")
	public ResponseEntity<List<ArticleResponseDto>> getMemberArticles(
			@PathVariable Long memberId,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		List<ArticleResponseDto> memberArticles = articleService.getMemberArticles(memberId, page, size);
		return ResponseEntity.ok(memberArticles);
	}
	// 특정 단체의 소식지 목록 조회
	// GET /api/v1/articles/groups/{groupId}?page={page}&size={size} (pending)
	@Operation(summary = "특정 단체의 소식지 목록 조회", description = "특정 단체의 소식지 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping(value = "/groups/{groupId}")
	public ResponseEntity<List<ArticleResponseDto>> getGroupArticles(
			@PathVariable Long groupId,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		List<ArticleResponseDto> groupArticles = articleService.getGroupArticles(groupId, page, size);
		return ResponseEntity.ok(groupArticles);
	}
	// 관심 소식지 등록
	// PUT /api/v1/articles/{articleId}/like (pending)
	@Operation(summary = "관심 소식지 등록", description = "소식지를 관심 목록에 등록합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@PutMapping(value = "/{articleId}/like")
	public ResponseEntity<Void> likeArticle(@PathVariable Long articleId) {
		articleService.likeArticle(articleId);
		return ResponseEntity.ok().build();
	}
	// 관심 소식지 해제
	// DELETE /api/v1/articles/{articleId}/like (pending)
	@Operation(summary = "관심 소식지 해제", description = "소식지를 관심 목록에서 해제합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@DeleteMapping(value = "/{articleId}/like")
	public ResponseEntity<Void> unlikeArticle(@PathVariable Long articleId) {
		articleService.unlikeArticle(articleId);
		return ResponseEntity.ok().build();
	}
}

