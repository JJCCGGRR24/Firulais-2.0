
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Servicce;

@Repository
public interface ServicceRepository extends JpaRepository<Servicce, Integer> {

	@Query("select r.servicce from Request r where r.rendezvous.id =?1")
	List<Servicce> servicesByRendezvous(int rendezvousId);

}
