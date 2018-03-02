
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id=?1")
	User findByPrincipal(int id);

	//queries for dashboard

	@Query("select avg(u.rendezvouses.size), stddev(u.rendezvouses.size) * 1.0 from User u")
	Double[] queryC1();

	@Query("select count(u) * 1.0/ (select count(uu) * 1.0 from User uu where uu.rendezvouses.size = 0) from User u where u.rendezvouses.size >= 1")
	Double queryC2();

	@Query("select count(uu) * 1.0 from User uu where uu.rendezvouses.size = 0")
	Double queryC2denominador();

	@Query("select avg(u.RSVPd.size), stddev(u.RSVPd.size) *1.0 from User u")
	Double[] queryC4();

}
