
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	@Query("select r from Reply r where r.comment.id =?1")
	List<Reply> repliesByComment(int commentId);

}
