
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QueriesServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private CommentService		commentService;

	@Autowired
	private UserService			userService;


	// Tests ------------------------------------------------------------------

	@Test
	public void queries() {

		final Double[] queryC1 = this.userService.queryC1();
		Assert.notNull(queryC1);
		final Double queryC2 = this.userService.queryC2();
		Assert.notNull(queryC2);
		final Double[] queryC3 = this.rendezvousService.queryC3();
		Assert.notNull(queryC3);
		final Double[] queryC4 = this.userService.queryC4();
		Assert.notNull(queryC4);
		final Collection<Rendezvous> queryC5 = this.rendezvousService.queryC5();
		Assert.notNull(queryC5);
		final Double[] queryB1 = this.rendezvousService.queryB1();
		Assert.notNull(queryB1);
		final Collection<Rendezvous> queryB2 = this.rendezvousService.queryB2();
		Assert.notNull(queryB2);
		final Collection<Rendezvous> queryB3 = this.rendezvousService.queryB3();
		Assert.notNull(queryB3);
		final Double[] queryA1 = this.rendezvousService.queryA1();
		Assert.notNull(queryA1);
		final Double[] queryA2 = this.rendezvousService.queryA2();
		Assert.notNull(queryA2);
		final Double[] queryA3 = this.commentService.queryA3();
		Assert.notNull(queryA3);

	}
}
