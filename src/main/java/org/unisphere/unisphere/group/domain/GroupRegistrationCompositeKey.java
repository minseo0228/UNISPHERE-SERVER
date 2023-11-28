package org.unisphere.unisphere.group.domain;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class GroupRegistrationCompositeKey implements Serializable {

	@Column(name = "member_id", nullable = false, insertable = false, updatable = false)
	private Long memberId;

	@Column(name = "group_id", nullable = false, insertable = false, updatable = false)
	private Long groupId;

	protected GroupRegistrationCompositeKey(Long memberId, Long groupId) {
		this.memberId = memberId;
		this.groupId = groupId;
	}

	public static GroupRegistrationCompositeKey of(Long memberId, Long groupId) {
		return new GroupRegistrationCompositeKey(memberId, groupId);
	}
}
