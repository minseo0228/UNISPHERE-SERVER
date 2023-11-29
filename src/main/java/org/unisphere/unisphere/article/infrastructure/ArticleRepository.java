package org.unisphere.unisphere.article.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unisphere.unisphere.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
