
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select n from Newspaper n where n.publicationDate != null")
	public List<Newspaper> getPublishedNewspapers();

	@Query("select a from Article a where a.newspaper = ?1 and a.finalMode = false")
	public List<Newspaper> getArticlesNoPublished(Newspaper n);

	@Query("select n from Newspaper n where n.publicationDate != null and (n.title like %?1% or n.description like %?1%)")
	public Collection<Newspaper> search(String search);
}
