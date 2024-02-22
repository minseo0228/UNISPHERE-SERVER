package org.unisphere.unisphere.group.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unisphere.unisphere.annotation.Logging;
import org.unisphere.unisphere.exception.ExceptionStatus;
import org.unisphere.unisphere.group.domain.Group;
import org.unisphere.unisphere.group.domain.GroupRegistration;
import org.unisphere.unisphere.group.infrastructure.GroupRegistrationRepository;
import org.unisphere.unisphere.group.infrastructure.GroupRepository;
import org.unisphere.unisphere.member.domain.Member;

@Service
@RequiredArgsConstructor
@Logging
public class GroupQueryService {

	private final GroupRepository groupRepository;
	private final GroupRegistrationRepository groupRegistrationRepository;

	/**
	 * 전체 그룹 정보 조회
	 *
	 * @param pageable 페이징 정보
	 * @return 전체 그룹 정보 (Page)
	 */
	public Page<Group> findAllGroups(Pageable pageable) {
		return groupRepository.findAll(pageable);
	}

	/**
	 * 회원이 속한 그룹 정보 조회
	 *
	 * @param memberId 회원 ID
	 * @param pageable 페이징 정보
	 * @return 회원이 속한 그룹 정보 (Page)
	 */
	public Page<GroupRegistration> findMemberGroups(Long memberId, Pageable pageable) {
		return groupRegistrationRepository.findAllByMemberId(
				memberId, pageable);
	}

	/**
	 * 그룹에 속한 회원 정보 조회
	 *
	 * @param groupId  그룹 ID
	 * @param pageable 페이징 정보
	 * @return 그룹에 속한 회원 정보 (Page)
	 */
	public Page<GroupRegistration> findGroupMembers(Long groupId, Pageable pageable) {
		return groupRegistrationRepository.findAllByGroupId(
				groupId, pageable);
	}

	/**
	 * 그룹 이름으로 Group 조회
	 *
	 * @param name 그룹 이름
	 * @return 조회된 Group 객체
	 */
	public Optional<Group> findGroupByName(String name) {
		return groupRepository.findByName(name);
	}

	/**
	 * 그룹 ID로 Group 정보를 가져온다.
	 *
	 * @param groupId 그룹 ID
	 * @return Group 객체
	 */
	public Group getGroup(Long groupId) {
		Optional<Group> group = groupRepository.findById(groupId);
		return group.orElseThrow(ExceptionStatus.NOT_FOUND_GROUP::toServiceException);
	}
}
