package org.unisphere.unisphere.auth.oauth;

import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.unisphere.unisphere.auth.oauth.extractor.OauthAttributeExtractor;
import org.unisphere.unisphere.auth.service.AuthService;
import org.unisphere.unisphere.config.ClientConfig;
import org.unisphere.unisphere.member.domain.Member;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OauthHandler {

	private final AuthService authService;
	private final ClientConfig clientConfig;

	@Bean
	public AuthenticationSuccessHandler oauthAuthenticationSuccessHandler() {
		return (request, response, authentication) -> {
			DefaultOAuth2User auth2User = (DefaultOAuth2User) authentication.getPrincipal();
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
			String providerName = token.getAuthorizedClientRegistrationId();
			OauthAttributeExtractor extractor = OauthAttributeExtractor.create(
					auth2User, providerName
			);
			Member member = authService.createAndFindOauthSocialMember(extractor);
			log.info("Member Login Success: {}", member);
//          TODO: JWT 토큰 발행 로직 추가
//			String jwtToken = jwtTokenProvider.createCommonAccessToken(member.getId())
//					.getTokenValue();
//			cookieManager.createCookie(
//					response, jwtToken
//			);
			response.sendRedirect(
					clientConfig.getClientUrl() + clientConfig.getClientCallbackUrl()
							+ "?isFirstLogin="
							+ member.isFirstLogin());
		};
	}

	@Bean
	public AuthenticationFailureHandler oauthAuthenticationFailureHandler() {
		return (request, response, exception) -> {
			log.error("OAuth2 Login Failure", exception);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		};
	}
}
