package org.unisphere.unisphere.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ClientConfig {

	@Value("${unisphere.client.url}")
	private String clientUrl;

	@Value("${unisphere.client.http-url}")
	private String clientHttpUrl;

	@Value("${unisphere.client.callback}")
	private String clientCallbackUrl;
}