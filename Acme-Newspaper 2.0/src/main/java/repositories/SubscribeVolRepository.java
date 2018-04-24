
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SubscribeVol;

@Repository
public interface SubscribeVolRepository extends JpaRepository<SubscribeVol, Integer> {

}
