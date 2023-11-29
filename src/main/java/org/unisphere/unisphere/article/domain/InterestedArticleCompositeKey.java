package org.unisphere.unisphere.article.domain;

import static lombok.AccessLevel.PROTECTED;

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
public class InterestedArticleCompositeKey implements java.io.Serializable {

	@Column(name = "member_id", nullable = false, insertable = false, updatable = false)
	private Long memberId;

	@Column(name = "article_id", nullable = false, insertable = false, updatable = false)
	private Long articleId;

	protected InterestedArticleCompositeKey(Long memberId, Long articleId) {
		this.memberId = memberId;
		this.articleId = articleId;
	}

	public static InterestedArticleCompositeKey of(Long memberId, Long articleId) {
		return new InterestedArticleCompositeKey(memberId, articleId);
	}
}
