
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;
import domain.FollowUp;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

	@Query("delete from FollowUp where article = ?1")
	void deleteFollowUps(Article a);

	@Query("select a  from FollowUp a where a.article.newspaper.user.id = ?1 order by a.publicationMoment desc")
	List<FollowUp> getFollowUpsByUser(int user);
}
