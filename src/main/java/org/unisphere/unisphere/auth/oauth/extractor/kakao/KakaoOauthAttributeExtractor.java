package org.unisphere.unisphere.auth.oauth.extractor.kakao;


import java.util.Objects;
import lombok.ToString;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.unisphere.unisphere.auth.domain.OauthType;
import org.unisphere.unisphere.auth.oauth.extractor.OauthAttributeExtractor;

@ToString
public class KakaoOauthAttributeExtractor implements OauthAttributeExtractor {

	private final OAuth2User auth2User;

	public KakaoOauthAttributeExtractor(OAuth2User auth2User) {
		this.auth2User = auth2User;
	}

	@Override
	public String getEmail() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "kakao_account",
				"email");
	}

	@Override
	public String getNickname() {
		return OauthAttributeExtractor.getNestedAttribute(auth2User, "properties", "nickname");
	}

	@Override
	public OauthType getOauthType() {
		return OauthType.KAKAO;
	}

	@Override
	public String getOauthId() {
		return Objects.requireNonNull(auth2User.getAttribute("id")).toString();
	}
}
