package org.unisphere.unisphere.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unisphere.unisphere.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
