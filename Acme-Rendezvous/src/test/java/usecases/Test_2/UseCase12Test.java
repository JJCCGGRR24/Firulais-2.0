
package usecases.Test_2;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ConfigurationService;
import utilities.AbstractTest;
import domain.Configuration;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase12Test extends AbstractTest {

	//	12. Acme Rendezvous, Inc. is franchising their business. They require the following data to customise
	//	their system for particular franchisees: the name of the business, a banner, and a
	//	welcome message (which must be available in the languages in which the system’s available).
	//	This information must be displayed appropriately in the header of the pages and the
	//	welcome page. Administrators must be allowed to change the previous data at runtime.

	// System under test ------------------------------------------------------
	@Autowired
	private ConfigurationService	configurationService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"admin", null
			}, {
				"manager1", IllegalArgumentException.class
			}
		};
		System.out.println("Test Caso de Uso 12");
		System.out.println("");
		for (int i = 0; i < testingData.length; i++) {
			System.out.println("Test " + (i + 1) + ":");
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Modifico el nombre del sistema
			final Configuration c = this.configurationService.find();
			c.setNameBusiness("Acme Nuevo Nombre");

			//Guardo
			this.configurationService.save(c);
			this.configurationService.flush();

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Nombre modificado correctamente: " + this.configurationService.find().getNameBusiness());
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
		}

		this.checkExceptions(expected, caught);

		System.out.println("");
	}
}
