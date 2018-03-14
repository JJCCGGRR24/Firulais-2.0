
package usecases.tests_v1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import services.UserService;
import utilities.AbstractTest;
import domain.Rendezvous;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_1Test extends AbstractTest {

	//	5. An actor who is authenticated as a user must be able to:
	//		1. Do the same as an actor who is not authenticated, but register to the system.

	// System under test ------------------------------------------------------
	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				// Siendo un user, accedo a la lista de usuarios y a los rendezvous a los que asistira un usuario y luego accedo a la lista de rendezvous y al perfil del creador de un rendezvous y los asistentes.
				"user1", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			//accedo a la lista de usuarios
			final List<User> lista = (List<User>) this.userService.findAll();
			//Accedo a la lista de rendezvous a los que ha asistido o asistira un user
			lista.get(0).getRSVPd();

			this.unauthenticate();

			this.userService.flush();

			this.authenticate(username);

			//accedo a la lista de rendezvous
			final List<Rendezvous> lista1 = (List<Rendezvous>) this.rendezvousService.findAll();
			//Accedo a la lista de rendezvous a los que ha asistido o asistira un user
			lista1.get(0).getUser();
			lista1.get(0).getUsers();

			this.unauthenticate();

			this.rendezvousService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
