package org.unisphere.unisphere.group.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unisphere.unisphere.group.infrastructure.GroupRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupFacadeService {

	private final GroupRepository groupRepository;
}
