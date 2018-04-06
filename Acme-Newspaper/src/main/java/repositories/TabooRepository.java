
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Taboo;

@Repository
public interface TabooRepository extends JpaRepository<Taboo, Integer> {

}
