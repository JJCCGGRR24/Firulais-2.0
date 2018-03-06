
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	//queries for dashboard
	@Query("select m from Manager m where m.servicces.size > (select avg(mm.servicces.size) from Manager mm)")
	List<Manager> queryNewC2();

	@Query("select max(m) from Manager m join m.servicces s where s.cancelled = true")
	List<Manager> queryNewC3();

}
