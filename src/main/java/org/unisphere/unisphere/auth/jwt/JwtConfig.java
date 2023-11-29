package org.unisphere.unisphere.auth.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfig {

	public static final String ROLES = "roles";
	public static final String SCOPES = "scopes";
	public static final String MEMBER_ID = "memberId";

	@Value("${unisphere.jwt.common-token-expire-time}")
	private long commonTokenExpireTime;

	@Value("${unisphere.jwt.refresh-token-expire-time}")
	private long refreshTokenExpireTime;
}
