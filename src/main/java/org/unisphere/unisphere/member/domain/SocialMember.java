package org.unisphere.unisphere.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.unisphere.unisphere.auth.domain.OauthType;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "member_id", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	private Member member;

	@Column(nullable = false)
	private String oauthId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OauthType oauthType;
}
