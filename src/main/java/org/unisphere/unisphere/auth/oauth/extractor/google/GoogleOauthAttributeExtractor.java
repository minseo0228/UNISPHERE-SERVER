package org.unisphere.unisphere.auth.oauth.extractor.google;

import lombok.ToString;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.unisphere.unisphere.auth.domain.OauthType;
import org.unisphere.unisphere.auth.oauth.extractor.OauthAttributeExtractor;

@ToString
public class GoogleOauthAttributeExtractor implements OauthAttributeExtractor {

	private final OAuth2User auth2User;

	public GoogleOauthAttributeExtractor(OAuth2User auth2User) {
		this.auth2User = auth2User;
	}

	@Override
	public String getEmail() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "email");
	}

	@Override
	public String getNickname() {
		System.out.println("auth2User = " + auth2User);
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "name");
	}

	@Override
	public OauthType getOauthType() {
		return OauthType.GOOGLE;
	}

	@Override
	public String getOauthId() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "sub");
	}
}
