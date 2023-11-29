package org.unisphere.unisphere.auth.jwt;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.unisphere.unisphere.auth.domain.MemberRole;

@Component
@RequiredArgsConstructor
/*
  JWT 토큰 생성을 위한 클래스
 */
public class JwtTokenProvider {

	private final JwtEncoder jwtEncoder;
	private final JwtConfig jwtConfig;

	/**
	 * 주어진 ID를 가진 회원의 Common Access JWT Token을 생성한다.
	 *
	 * @param id 회원의 ID
	 * @return 생성된 JWT Token
	 */
	public Jwt createCommonAccessToken(Long id) {
		Instant now = Instant.now();
		JwsHeader header = JwsHeader
				.with(MacAlgorithm.HS256)
				.build();

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuedAt(now)
				.expiresAt(now.plusSeconds(jwtConfig.getCommonTokenExpireTime()))
				.claim(JwtConfig.MEMBER_ID, id)
				.claim(JwtConfig.ROLES, List.of(MemberRole.S_USER))
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims));
	}
}

