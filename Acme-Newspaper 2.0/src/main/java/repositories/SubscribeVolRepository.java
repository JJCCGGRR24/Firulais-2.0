
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Subscribe;
import domain.SubscribeVol;
import domain.Volume;

@Repository
public interface SubscribeVolRepository extends JpaRepository<SubscribeVol, Integer> {

	@Query("select s from SubscribeVol s where s.customer = ?1 and s.volume = ?2")
	public Subscribe getSubscripcion(Customer c, Volume n);

}
