
package usecases.tests_v1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import utilities.AbstractTest;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase04_3Test extends AbstractTest {

	//	4. An actor who is not authenticated must be able to:
	//		 3. List the rendezvouses in the system and navigate to the profiles of the corresponding creators and attendants.

	// System under test ------------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				// Siendo un actor no autenticado, accedo a la lista de rendezvous y al perfil del creador de un rendezvous y los asistentes.
				null, null
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

			//accedo a la lista de rendezvous
			final List<Rendezvous> lista = (List<Rendezvous>) this.rendezvousService.findAll();
			//Accedo a la lista de rendezvous a los que ha asistido o asistira un user
			lista.get(0).getUser();
			lista.get(0).getUsers();

			this.unauthenticate();

			this.rendezvousService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
