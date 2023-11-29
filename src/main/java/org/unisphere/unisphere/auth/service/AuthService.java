package org.unisphere.unisphere.auth.service;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unisphere.unisphere.auth.domain.MemberRole;
import org.unisphere.unisphere.auth.oauth.extractor.OauthAttributeExtractor;
import org.unisphere.unisphere.member.domain.Member;
import org.unisphere.unisphere.member.infrastructure.MemberRepository;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;

	public Member createAndFindOauthSocialMember(OauthAttributeExtractor extractor) {
		log.info("Called createAndFindOauthSocialMember: extractor = {}", extractor);
		return memberRepository.findByEmail(extractor.getEmail())
				.orElseGet(() ->
						memberRepository.save(
								Member.createSocialMember(
										extractor.getEmail(),
										extractor.getNickname() + UUID.randomUUID(),
										LocalDateTime.now(),
										MemberRole.COMMON,
										extractor.getOauthId(),
										extractor.getOauthType()
								)
						)
				);
	}
}
