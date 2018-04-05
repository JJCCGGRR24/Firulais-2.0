
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	//DASHBOARD-------------------------------------------------------------------------

	//LEVEL C

	@Query("select avg(u.newspapers.size), stddev(u.newspapers.size) from User u")
	Double[] queryC1();

	@Query("select avg(u.articles.size), stddev(u.articles.size) from User u")
	Double[] queryC2();

	@Query("select avg(n.articles.size), stddev(n.articles.size) from Newspaper n")
	Double[] queryC3();

	@Query("select n.title from Newspaper n where (n.articles.size*0.1)>=(avg(n))")
	Object[] queryC4();

	@Query("select n.title from Newspaper n where (n.articles.size*0.1)<=(avg(n))")
	Object[] queryC5();

	@Query("select (count(u)*1.0)/(select count(uu) from User uu) from User u where u.newspapers.size < 0")
	Double queryC6();

	@Query("select (count(u)*1.0)/(select count(uu) from User uu) from User u where u.articles.size < 0")
	Double queryC7();

	//LEVEL B

	@Query("select avg(a.followUp.size) from Article a")
	Double queryB1();

	@Query("select avg(a.followUp.size) from Article a where week(a.moment) <= week(CURRENT_DATE)+1")
	Double queryB2();

	@Query("select avg(a.followUp.size) from Article a where week(a.moment) <= week(CURRENT_DATE)+2")
	Double queryB3();

	@Query("select avg(u.chirps.size), stddev(u.chirps.size) from User u")
	Double[] queryB4();

	@Query("select (count(u)*1.0)/(select count(uu) from User uu) from User u where (u.chirps.size *0.75) > avg(u.chirps.size)")
	Double queryB5();

	//LEVEL A

	@Query("select (count(u)*1.0)/(select count(nn) from Newspaper nn where nn.deprived = 1) from Newspaper n where n.deprived = 0")
	Double queryA1();

	@Query("select avg(n.articles.size) from Newspaper n where n.deprived = 1")
	Double queryA2();

	@Query("select avg(n.articles.size) from Newspaper n where n.deprived = 0")
	Double queryA3();

	@Query("select (count(s)*1.0)/(select count(u) from User u) from Subscribe s where s.newspaper.deprived = 1")
	Double queryA4();

	@Query("select count(n)/(select count(t) from Newspaper t) from Newspaper n where n.deprived = 1")
	Double queryA5();

}
