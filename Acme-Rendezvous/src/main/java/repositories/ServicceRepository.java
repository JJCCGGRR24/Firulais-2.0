
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Servicce;

@Repository
public interface ServicceRepository extends JpaRepository<Servicce, Integer> {

	@Query("select s from Servicce s where s.requests.size = (select max(ss.requests.size) from Servicce ss) and s.cancelled = false")
	List<Servicce> queryNewC1B4();

	@Query("select r.servicce from Request r where r.rendezvous.id =?1")
	List<Servicce> servicesByRendezvous(int rendezvousId);

	@Query("select s from Servicce s where s.cancelled = false")
	Collection<Servicce> serviccesVisibles();

}
