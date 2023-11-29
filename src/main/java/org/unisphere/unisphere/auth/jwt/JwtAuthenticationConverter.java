package org.unisphere.unisphere.auth.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final String SCOPE_PREFIX = "SCOPE_";

	@Override
	/*
	  Jwt를 JwtAuthenticationToken으로 변환한다.
	  @param jwt 변환할 Jwt
	 * @return JwtAuthenticationToken 변환된 JwtAuthenticationToken
	 */
	public JwtAuthenticationToken convert(Jwt jwt) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (jwt.hasClaim(JwtConfig.ROLES)) {
			authorities.addAll(
					jwt.getClaimAsStringList(JwtConfig.ROLES)
							.stream()
							.map(role -> ROLE_PREFIX + role)
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList())
			);
		}
		if (jwt.hasClaim(JwtConfig.SCOPES)) {
			authorities.addAll(jwt.getClaimAsStringList(JwtConfig.SCOPES).stream()
					.map(scope -> SCOPE_PREFIX + scope)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList())
			);
		}
		return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
	}
}
