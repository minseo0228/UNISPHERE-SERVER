package org.unisphere.unisphere.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CookieConfig {

	@Value("${unisphere.cookie.domain}")
	private String domain;

	@Value("${unisphere.cookie.name}")
	private String name;

	@Value("${unisphere.cookie.path}")
	private String path;

	@Value("${unisphere.cookie.max-age}")
	private int maxAge;

	@Value("${unisphere.cookie.http-only}")
	private boolean httpOnly;

	@Value("${unisphere.cookie.secure}")
	private boolean secure;

	@Value("${unisphere.cookie.same-site}")
	private String sameSite;
}
