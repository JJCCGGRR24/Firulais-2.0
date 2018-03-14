
package usecases.Test_2;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Servicce;
import services.ServicceService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_1Test extends AbstractTest {

	//	5. An actor who is registered as a manager must be able to:
	//		1. List the services that are available in the system.

	// System under test ------------------------------------------------------
	@Autowired
	private ServicceService servicceService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"manager1", null
			}, {
				"manager11", IllegalArgumentException.class //Un manager que no existe
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

			//Nos autenticamos como MANAGER
			this.authenticate(username);

			//Muestro todos los Servicces del sistema
			final ArrayList<Servicce> list = new ArrayList<>(this.servicceService.findAll());
			for (final Servicce servicce : list)
				System.out.println(servicce.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
		}

		this.checkExceptions(expected, caught);

		System.out.println("-----------------------------");
	}

}
