package org.unisphere.unisphere.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ServerConfig {

	@Value("${unisphere.server.host}")
	private String host;

	@Value("${unisphere.server.oauth2.login-endpoint}")
	private String oauth2LoginEndpoint;

	@Value("${unisphere.server.oauth2.redirect-uri}")
	private String oauth2CallbackEndpoint;
}
