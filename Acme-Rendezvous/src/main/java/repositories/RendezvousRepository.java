
package repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r join r.users u where u.id = ?1 and (r.moment < CURRENT_TIMESTAMP)")
	List<Rendezvous> listRSVPdPast(int userId);

	@Query("select r from Rendezvous r join r.users u where u.id = ?1 and (r.moment > CURRENT_TIMESTAMP)")
	List<Rendezvous> listRSVPdFuture(int userId);

	@Query("select r from Rendezvous r where r.moment < CURRENT_TIMESTAMP")
	List<Rendezvous> listRendezvousPast();

	@Query("select r from Rendezvous r where r.moment > CURRENT_TIMESTAMP")
	List<Rendezvous> listRendezvousFuture();

	@Query("select r from Rendezvous r where r.moment < CURRENT_TIMESTAMP and r.adultsOnly = false")
	List<Rendezvous> listRendezvousPastFF();

	@Query("select r from Rendezvous r where r.moment > CURRENT_TIMESTAMP and r.adultsOnly = false")
	List<Rendezvous> listRendezvousFutureFF();

	//	@Query("select r from Rendezvous r join r.users u where u.id = ?1 and (r.moment < CURRENT_TIMESTAMP)")
	//	List<Rendezvous> listRSVPdPast(int id);
	//
	//	@Query("select r from Rendezvous r join r.users u where u.id = ?1 and (r.moment > CURRENT_TIMESTAMP)")
	//	List<Rendezvous> listRSVPdFuture(int id);
	//
	//	@Query("select r from Rendezvous r join r.users u where u.id = ?1")
	//	List<Rendezvous> listRSVPd(int id);

	@Query("select r from Rendezvous r where r.user.id = ?1 and (r.moment < CURRENT_TIMESTAMP)")
	List<Rendezvous> listMyPastRendezvous(int id);

	@Query("select r from Rendezvous r where r.user.id = ?1 and (r.moment > CURRENT_TIMESTAMP)")
	List<Rendezvous> listMyFutureRendezvous(int id);

	@Query("select r from Rendezvous r where r.deleted = false and (r.moment > CURRENT_TIMESTAMP)")
	List<Rendezvous> posibleToLink();

	//queries for dashboard

	@Query("select avg(r.users.size), stddev(r.users.size) * 1.0 from Rendezvous r")
	Double[] queryC3();

	@Query("select r from Rendezvous r where r.users.size > 0 order by r.users.size desc")
	Page<Rendezvous> queryC5(Pageable pageable);

	@Query("select avg(r.announcements.size), stddev(r.announcements.size) * 1.0  from Rendezvous r")
	Double[] queryB1();

	@Query("select r from Rendezvous r where r.announcements.size > (select avg(rr.announcements.size) * 0.75 from Rendezvous rr)")
	List<Rendezvous> queryB2();

	@Query("select r from Rendezvous r where r.linkedTo.size > (select avg(rr.linkedTo.size) * 1.1 from Rendezvous rr)")
	List<Rendezvous> queryB3();

	@Query("select avg(r.questions.size), stddev(r.questions.size) * 1.0  from Rendezvous r")
	Double[] queryA1();

	@Query("select count(a) * 1.0 from Answer a")
	Double queryA2partAnswer();

	@Query("select count(r) * 1.0 from Rendezvous r")
	Double queryA2partRendezvous();

	@Query("select (select count (a) from Answer a where a.question.rendezvous.id=r.id) from Rendezvous r")
	List<Long> queryA2partSttdev();

	//	@Query("select avg(r.servicces.size) from Rendezvous r join r.servicces s group by s.category")
	//	Double queryNewB1();
}
