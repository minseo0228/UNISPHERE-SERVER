package org.unisphere.unisphere.article.domain;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.unisphere.unisphere.member.domain.Member;

@Entity
@Table(name = "interested_article")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestedArticle {

	@EmbeddedId
	@ToString.Exclude
	private InterestedArticleCompositeKey id;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
	private Member member;

	@ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
	@JoinColumn(name = "article_id", nullable = false, insertable = false, updatable = false)
	private Article article;
}
