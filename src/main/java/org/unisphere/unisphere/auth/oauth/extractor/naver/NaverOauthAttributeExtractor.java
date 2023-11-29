package org.unisphere.unisphere.auth.oauth.extractor.naver;

import lombok.ToString;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.unisphere.unisphere.auth.domain.OauthType;
import org.unisphere.unisphere.auth.oauth.extractor.OauthAttributeExtractor;

@ToString
public class NaverOauthAttributeExtractor implements OauthAttributeExtractor {

	private final OAuth2User auth2User;

	public NaverOauthAttributeExtractor(OAuth2User auth2User) {
		this.auth2User = auth2User;
	}

	@Override
	public String getEmail() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "response", "email");
	}

	@Override
	public String getNickname() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "response", "nickname");
	}

	@Override
	public OauthType getOauthType() {
		return OauthType.NAVER;
	}

	@Override
	public String getOauthId() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "response", "id");
	}
}
