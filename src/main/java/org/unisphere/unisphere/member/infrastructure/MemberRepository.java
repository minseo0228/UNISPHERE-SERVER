package org.unisphere.unisphere.member.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.unisphere.unisphere.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("select m from Member m where m.email = :email")
	Optional<Member> findByEmail(String email);
}
