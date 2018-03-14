
package usecases.Test_2;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ServicceService;
import utilities.AbstractTest;
import domain.Servicce;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase04_2Test extends AbstractTest {

	//An actor who is authenticated as a user must be able to list the services that are available in the system.

	// System under test ------------------------------------------------------
	@Autowired
	private ServicceService	serviceService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {

			//Intentamos listar los servicios autenticados como User. El resultado del test debe ser positivo
			{
				"user1", null
			},
			//Intentamos listar los servicios sin estar autenticado como User. En este caso el resultado del test debe ser negativo. 
			{
				"churrasquito", IllegalArgumentException.class
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

			//Nos autenticamos
			this.authenticate(username);

			//Listamos los servicios
			final Collection<Servicce> r = this.serviceService.findAll();

			//Nos deslogueamos
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
