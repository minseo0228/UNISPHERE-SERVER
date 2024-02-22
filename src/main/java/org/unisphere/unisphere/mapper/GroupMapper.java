package org.unisphere.unisphere.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.unisphere.unisphere.group.domain.Group;
import org.unisphere.unisphere.group.dto.GroupPreviewDto;
import org.unisphere.unisphere.group.dto.response.GroupAvatarResponseDto;
import org.unisphere.unisphere.group.dto.response.GroupHomePageResponseDto;
import org.unisphere.unisphere.group.dto.response.GroupListResponseDto;

@Mapper(componentModel = "spring")
public interface GroupMapper {

	GroupMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(GroupMapper.class);

	@Mapping(source = "group.id", target = "groupId")
	GroupPreviewDto toGroupPreviewDto(Group group);

	@Mapping(source = "totalElements", target = "totalGroupCount")
	@Mapping(source = "groupPreviewDtos", target = "groups")
	GroupListResponseDto toGroupListResponseDto(List<GroupPreviewDto> groupPreviewDtos,
			long totalElements);

	@Mapping(source = "group.id", target = "groupId")
	@Mapping(source = "avatarImageUrl", target = "avatarImageUrl")
	GroupAvatarResponseDto toGroupAvatarResponseDto(Group group, String avatarImageUrl);

	@Mapping(source = "group.id", target = "groupId")
	@Mapping(source = "avatarImageUrl", target = "avatarImageUrl")
	@Mapping(source = "logoImageUrl", target = "logoImageUrl")
	GroupHomePageResponseDto toGroupHomePageResponseDto(Group group, String avatarImageUrl,
			String logoImageUrl);
}
