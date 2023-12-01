package org.unisphere.unisphere.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.unisphere.unisphere.annotation.LoginMemberInfo;
import org.unisphere.unisphere.auth.domain.MemberRole;
import org.unisphere.unisphere.auth.domain.OauthType;
import org.unisphere.unisphere.auth.dto.MemberSessionDto;
import org.unisphere.unisphere.member.domain.Member;
import org.unisphere.unisphere.member.infrastructure.MemberRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
@Tag(name = "인증", description = "인증 관련 API")
public class AuthController {

	private final MemberRepository memberRepository;

	@Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 요청합니다. 회원과 관련된 모든 정보가 삭제되며, 소셜 인증 기관에 revoke 요청을 수행합니다.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "no content"),
	})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/unregister")
	@Secured(MemberRole.S_USER)
	// TODO: 추후 탈퇴 로직 구현 필요
	public void unregister(@LoginMemberInfo MemberSessionDto memberSessionDto) {
		log.info("Called unregister member: {}", memberSessionDto);
	}

	@GetMapping(value = "/test")
	public String test() {
		log.info("Called test");
		Member socialMember = Member.createSocialMember(
				"email",
				"nickname",
				LocalDateTime.now(),
				MemberRole.MEMBER,
				"oauthId",
				OauthType.GOOGLE
		);
		System.out.println(socialMember);
		memberRepository.save(socialMember);
		return socialMember.toString();
	}
}
