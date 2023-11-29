package org.unisphere.unisphere.group.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unisphere.unisphere.group.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
