package org.unisphere.unisphere.group.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Schema(description = "단체 홈피 조회 응답")
public class GroupHomePageResponseDto {

	@Schema(description = "단체 ID", example = "1")
	private final Long groupId;

	@Schema(description = "단체 이름", example = "유니스피어")
	private final String name;

	@Schema(description = "단체 로고 이미지 URL", example = "https://unisphere.org/group-images/random-string/logo-image.png")
	private final String logoImageUrl;

	@Schema(description = "단체 요약 설명", example = "유니스피어는 멋진 단체에요.")
	private final String summary;

	@Schema(description = "단체 홈피 내용", example = "어쩌구 저쩌구... 단체들이 적고 싶은대로")
	private final String content;

	@Schema(description = "단체 사이트 URL", example = "https://unisphere.org")
	private final String groupSiteUrl;
}
