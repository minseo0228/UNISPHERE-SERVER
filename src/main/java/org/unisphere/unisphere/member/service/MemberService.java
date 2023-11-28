package org.unisphere.unisphere.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unisphere.unisphere.member.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
}
