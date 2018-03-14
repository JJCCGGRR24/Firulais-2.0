
package usecases.Test_1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.UserService;
import utilities.AbstractTest;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase04_2Test extends AbstractTest {

	//	4. An actor who is not authenticated must be able to:
	//		2. List the users of the system and navigate to their profiles, which include personal data and the list of rendezvouses that they’ve attended or are going to attend.

	// System under test ------------------------------------------------------
	@Autowired
	private UserService	userService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				// Siendo un actor no autenticado, accedo a la lista de usuarios y a los rendezvous a los que asistira un usuario.
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

			//accedo a la lista de usuarios
			final List<User> lista = (List<User>) this.userService.findAll();
			//Accedo a la lista de rendezvous a los que ha asistido o asistira un user
			lista.get(0).getRSVPd();

			this.unauthenticate();

			this.userService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
