package org.unisphere.unisphere.group.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unisphere.unisphere.annotation.Logging;
import org.unisphere.unisphere.exception.ExceptionStatus;
import org.unisphere.unisphere.group.domain.Group;
import org.unisphere.unisphere.group.domain.GroupRegistration;
import org.unisphere.unisphere.group.dto.GroupPreviewDto;
import org.unisphere.unisphere.group.dto.request.GroupAvatarUpdateRequestDto;
import org.unisphere.unisphere.group.dto.request.GroupCreateRequestDto;
import org.unisphere.unisphere.group.dto.response.GroupAvatarResponseDto;
import org.unisphere.unisphere.group.dto.response.GroupListResponseDto;
import org.unisphere.unisphere.image.service.ImageService;
import org.unisphere.unisphere.log.LogLevel;
import org.unisphere.unisphere.mapper.GroupMapper;
import org.unisphere.unisphere.member.domain.Member;
import org.unisphere.unisphere.member.service.MemberQueryService;

@Service
@RequiredArgsConstructor
@Logging(level = LogLevel.DEBUG)
public class GroupFacadeService {

	private final GroupQueryService groupQueryService;
	private final GroupCommandService groupCommandService;
	private final MemberQueryService memberQueryService;
	private final GroupMapper groupMapper;
	private ImageService imageService;

	@Transactional(readOnly = true)
	public GroupListResponseDto getAllGroups(Pageable pageable) {
		Page<Group> groups = groupQueryService.findAllGroups(pageable);
		List<GroupPreviewDto> groupPreviewDtos = groups.stream()
				.sorted(Comparator.comparing(Group::getName))
				.map(groupMapper::toGroupPreviewDto)
				.collect(Collectors.toList());
		return groupMapper.toGroupListResponseDto(groupPreviewDtos, groups.getTotalElements());
	}

	@Transactional(readOnly = true)
	public GroupListResponseDto getMyGroups(Long memberId, Pageable pageable) {
		Member member = memberQueryService.getMember(memberId);
		Page<GroupRegistration> groupRegistrations = groupQueryService.findMemberGroups(member,
				pageable);
		List<GroupPreviewDto> groupPreviewDtos = groupRegistrations.stream()
				.sorted(Comparator.comparing(
						groupRegistration -> groupRegistration.getGroup().getName()))
				.map(groupRegistration -> groupMapper.toGroupPreviewDto(
						groupRegistration.getGroup()))
				.collect(Collectors.toList());
		return groupMapper.toGroupListResponseDto(groupPreviewDtos,
				groupRegistrations.getTotalElements());
	}

	@Transactional(readOnly = true)
	public GroupListResponseDto getMemberGroups(Long memberId, Pageable pageable) {
		Member member = memberQueryService.getMember(memberId);
		Page<GroupRegistration> groupRegistrations = groupQueryService.findMemberGroups(member,
				pageable);
		List<GroupPreviewDto> groupPreviewDtos = groupRegistrations.stream()
				.sorted(Comparator.comparing(
						groupRegistration -> groupRegistration.getGroup().getName()))
				.map(groupRegistration -> groupMapper.toGroupPreviewDto(
						groupRegistration.getGroup()))
				.collect(Collectors.toList());
		return groupMapper.toGroupListResponseDto(groupPreviewDtos,
				groupRegistrations.getTotalElements());
	}

	@Transactional(readOnly = true)
	public GroupAvatarResponseDto getGroupAvatar(Long groupId) {
		Group group = groupQueryService.getGroup(groupId);
		return groupMapper.toGroupAvatarResponseDto(group, group.getAvatarImageUrl());
	}

	@Transactional
	public void createGroup(Long memberId, GroupCreateRequestDto groupCreateRequestDto) {
		Member member = memberQueryService.getMember(memberId);
		if (groupQueryService.findGroupByName(groupCreateRequestDto.getName()).isPresent()) {
			throw ExceptionStatus.ALREADY_EXIST_GROUP_NAME.toServiceException();
		}
		groupCommandService.createGroup(member, groupCreateRequestDto);
	}

	@Transactional
	public GroupAvatarResponseDto updateGroupAvatar(Long memberId, Long groupId,
			GroupAvatarUpdateRequestDto groupAvatarUpdateRequestDto) {
		Member member = memberQueryService.getMember(memberId);
		Group group = groupQueryService.getGroup(groupId);
		if (!group.isOwner(member)) {
			throw ExceptionStatus.NOT_GROUP_OWNER.toServiceException();
		}
		String imageUrl = imageService.findImageUrl(
				groupAvatarUpdateRequestDto.getPreSignedAvatarImageUrl());
		groupCommandService.updateGroupAvatar(group, groupAvatarUpdateRequestDto);
		return groupMapper.toGroupAvatarResponseDto(group, imageUrl);
	}
}
