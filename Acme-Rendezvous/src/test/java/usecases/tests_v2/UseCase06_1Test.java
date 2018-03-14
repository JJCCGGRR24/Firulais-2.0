
package usecases.tests_v2;

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
public class UseCase06_1Test extends AbstractTest {

	//	6. An actor who is authenticated as an administrator must be able to:
	//		1. Cancel a service that he or she finds inappropriate. Such services cannot be re-quested for any rendezvous. They must be flagged appropriately when listed.

	// System under test ------------------------------------------------------
	@Autowired
	private ServicceService	servicceService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				//Cancelamos un servicio correctamente
				"admin", "servicce1", null
			}, {
				//Intentamos cancelar un servicio ya cancelado.
				"admin", "servicce4", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String servicce, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			final Servicce servicio = this.servicceService.findOne(this.getEntityId(servicce));
			this.servicceService.cancell(servicio);

			this.unauthenticate();

			this.servicceService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
