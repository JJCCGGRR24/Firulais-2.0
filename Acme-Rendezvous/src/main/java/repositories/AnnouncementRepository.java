
package repositories;

import java.util.Collection;

import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
@Table(indexes = {
	@Index(columnList = "rendezvous")
})
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select a from Announcement a where a.rendezvous.id = ?1 order by a.moment desc")
	Collection<Announcement> getAnnouncementsByRendezvousId(int id);

}
