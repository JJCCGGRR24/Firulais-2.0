
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Servicce;

@Repository
public interface ServicceRepository extends JpaRepository<Servicce, Integer> {

}
