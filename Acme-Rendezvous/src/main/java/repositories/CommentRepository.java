
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	//queries for dashboard
	@Query("select avg(c.replies.size), stddev(c.replies.size) * 1.0  from Comment c")
	Double[] queryA3();

	@Query("select c from Comment c where c.rendezvous.id =?1")
	List<Comment> commentsByRendezvous(int rendezvousId);
}
