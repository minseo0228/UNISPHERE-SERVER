package org.unisphere.unisphere.auth.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.unisphere.unisphere.auth.domain.MemberAuthenticationToken;
import org.unisphere.unisphere.auth.domain.MemberRole;
import org.unisphere.unisphere.auth.dto.MemberSessionDto;
import org.unisphere.unisphere.auth.jwt.JwtConfig;
import org.unisphere.unisphere.exception.JwtAuthenticationTokenException;

@Transient
@Slf4j
@RequiredArgsConstructor
/*
 * JWT 토큰을 이용한 인증을 위한 커스텀 인증 필터
 */
public class MemberAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
			return;
		}
		try {
			if (authentication instanceof JwtAuthenticationToken) {
				JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
				MemberAuthenticationToken token = convert(jwtAuthenticationToken);
				context.setAuthentication(token);
				SecurityContextHolder.clearContext();
				SecurityContextHolder.setContext(context);
			}
		} catch (JwtAuthenticationTokenException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
		doFilter(request, response, filterChain);
	}

	/**
	 * JwtAuthenticationToken을 우리 시스템의 커스텀 MemberSessionAuthenticationToken으로 변환한다.
	 *
	 * @param token JwtAuthenticationToken 인증 토큰
	 * @return 변환된 커스텀 인증 토큰
	 */
	private MemberAuthenticationToken convert(JwtAuthenticationToken token) {
		Object target = token.getPrincipal();
		if (target instanceof Jwt) {
			Jwt jwt = (Jwt) target;
			MemberSessionDto principal = convertPrincipal(jwt);
			MemberAuthenticationToken ret = new MemberAuthenticationToken(principal,
					token.getAuthorities());
			ret.setAuthenticated(true);
			log.info("convert token principal: {}, authorities: {}", ret.getPrincipal(),
					token.getAuthorities());
			return ret;
		}
		throw new JwtAuthenticationTokenException("Jwt 토큰이 아닙니다.");
	}

	/**
	 * Jwt Principal 정보를 토대로 회원 인증 정보 DTO 객체를 생성한다.
	 *
	 * @param jwt Jwt Principal 정보
	 * @return 회원 인증 정보 DTO 객체
	 */
	private MemberSessionDto convertPrincipal(Jwt jwt) {
		Long memberId = jwt.getClaim(JwtConfig.MEMBER_ID);
		List<MemberRole> roles = jwt.getClaim(JwtConfig.ROLES);
		log.debug("memberId: {}, roles: {}", memberId, roles);
		if (memberId == null || roles == null) {
			throw new JwtAuthenticationTokenException("Jwt에 필요한 정보가 없습니다.");
		}
		return MemberSessionDto.builder()
				.memberId(memberId)
				.roles(roles)
				.build();
	}
}