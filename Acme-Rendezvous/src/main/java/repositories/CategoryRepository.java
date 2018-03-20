
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.name = ?1")
	Category get(String string);

	@Query("select c from Category c where c.categoryFather is null")
	List<Category> getFathers();

	//queries for dashboard

	@Query("select avg(c.servicces.size) from Category c")
	Double queryNewB2();

	//	@Query("select (select count(rq) from Request rq where rq.rendezvous.id = r.id) from Rendezvous r")
	@Query("select avg(r.requests.size)*1.0, min(r.requests.size)*1.0, max(r.requests.size)*1.0, stddev(r.requests.size) * 1.0 from Rendezvous r")
	Double[] queryNewB3();
}
