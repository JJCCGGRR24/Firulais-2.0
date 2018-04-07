
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	@Query("select c from Chirp c join c.user f where ?1 in elements(f.followers)")
	List<Chirp> getChirpsFromFolloweds(int id);

	@Query("select c from Chirp c where c.tabooWord is true")
	List<Chirp> getChirpsTabooWords();

}
