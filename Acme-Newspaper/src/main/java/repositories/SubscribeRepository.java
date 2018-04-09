
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.Newspaper;
import domain.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

	@Query("select s from Subscribe s where s.customer = ?1 and s.newspaper = ?2")
	public Subscribe getSubscripcion(Customer c, Newspaper n);
}
