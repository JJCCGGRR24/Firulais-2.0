
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Taboo;

@Repository
public interface TabooRepository extends JpaRepository<Taboo, Integer> {

	@Query("select t from Taboo t where t.word = ?1")
	public Taboo getTabooFromWord(String word);

	@Query("select t.word from Taboo t")
	public List<String> getTabooWords();
}
