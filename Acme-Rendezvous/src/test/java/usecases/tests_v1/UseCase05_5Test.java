
package usecases.tests_v1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.LoginService;
import services.RendezvousService;
import utilities.AbstractTest;
import domain.Rendezvous;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_5Test extends AbstractTest {

	//SUT

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private LoginService		loginService;


	//	5.
	//	List the rendezvouses that he or she’s RSVPd.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//comprobaremos que los rsvp del user1 son 2, los que hay en el populate
				"user1", 2, null
			}, {
				//comprobamos que no se muestran los rsvp de otro user
				"user1", 3, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	protected void template(final String username, final int tamaño, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final User principal = (User) this.loginService.getPrincipalActor();
			final List<Rendezvous> rsvp = principal.getRSVPd();
			Assert.isTrue(rsvp.size() == tamaño);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
