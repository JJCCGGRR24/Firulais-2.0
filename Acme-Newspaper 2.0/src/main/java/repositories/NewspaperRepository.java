
package repositories;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select n from Newspaper n where n.publicationDate != null")
	public List<Newspaper> getPublishedNewspapers();

	@Query("select a from Article a where a.newspaper = ?1 and a.finalMode = false")
	public List<Newspaper> getArticlesNoPublished(Newspaper n);

	@Query("select n from Newspaper n where n.publicationDate != null and n.deprived = false and (n.title like %?1% or n.description like %?1%)")
	public Collection<Newspaper> search(String search);

	@Query("select n from Newspaper n where n.tabooWord is true")
	List<Newspaper> getNewspaperTabooWords();

	@Query("select n from Newspaper n where n.publicationDate = null")
	public List<Newspaper> getNotPublishedNewspapers();

	@Query("select s.newspaper from Subscribe s where s.customer = ?1")
	public List<Newspaper> getNewspaperSubscribes(Customer customer);

	@Query("select n from Newspaper n join n.advertisements e where e.agent.id = ?1 and n.publicationDate != null")
	public Set<Newspaper> findByAgent(int id);

	@Query("select n from Newspaper n join n.advertisements e where e.agent.id != ?1 and n.publicationDate != null")
	public Set<Newspaper> findNoByAgent(int id);
}
