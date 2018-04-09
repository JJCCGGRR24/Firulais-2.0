
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;
import domain.FollowUp;
import domain.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query("select a from Article a where a.finalMode = true and a.user = ?1")
	List<Article> findByUser(User user);

	@Query("select a from Article a where a.newspaper.deprived = false and a.newspaper.publicationDate != null")
	List<Article> publicArticles();

	@Query("select a from Article a where a.newspaper.publicationDate != null and (a.title like %?1% or a.summary like %?1% or a.body like %?1%)")
	Collection<Article> search(String search);

	@Query("select a from Article a where a.tabooWord is true")
	List<Article> getArticlesTabooWords();

	@Query("select a from Article a where a.finalMode = true and a.newspaper.user.id = ?1")
	List<FollowUp> getPublishedArticlesByUser(int id);
}
