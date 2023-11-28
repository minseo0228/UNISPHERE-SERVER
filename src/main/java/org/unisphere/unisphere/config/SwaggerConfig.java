package org.unisphere.unisphere.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
		info = @Info(title = "Jungko-Server API", version = "v1")
//		TODO: JWT 인증 구현 후 주석 해제
//		security = @SecurityRequirement(name = "bearerAuth")
)
//@SecurityScheme(
//		name = "bearerAuth",
//		type = SecuritySchemeType.HTTP,
//		scheme = "bearer"
//)
public class SwaggerConfig {

	@Bean
	public OpenAPI getOpenAPI() {
		return new OpenAPI().components(new Components()
//      TODO: JWT 인증 구현 후 주석 해제
//				.addHeaders("Authorization",
//						new Header().description("Auth header").schema(new StringSchema()))
		);
	}
}