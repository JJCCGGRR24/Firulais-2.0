
package usecases.tests_v1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Rendezvous;
import forms.LinkForm;
import services.RendezvousService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_4Test extends AbstractTest {

	//	16. An actor who is authenticated as a user must be able to:
	//		4. Link one of the rendezvouses that he or she’s created to other similar rendezvouses.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"user4", "rendezvous4", "rendezvous5", null //Un user
			}, {
				"manager1", "rendezvous4", "rendezvous5", java.lang.IllegalArgumentException.class //Un manager
			}, {
				null, "rendezvous4", "rendezvous5", java.lang.IllegalArgumentException.class //Un anonimo
			}, {
				"user4", "rendezvous1", "rendezvous4", null //Linkar un rendezvous que no es suyo
			}, {
				"user4", "rendezvous4", "rendezvous100", java.lang.AssertionError.class //Linkar un rendezvous que no existe
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String rendezvous1, final String rendezvous2, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Buscamos los rendezvouses
			final int rendezvousId1 = this.getEntityId(rendezvous1);

			final int rendezvousId2 = this.getEntityId(rendezvous2);
			final Rendezvous rendezvous4 = this.rendezvousService.findOne(rendezvousId2);

			//Link Form
			final LinkForm l = new LinkForm();
			l.setId(rendezvousId1);
			l.setR(rendezvous4);

			//Linkamos los rendevouses
			this.rendezvousService.link(l);

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-----------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-----------------------------");
		}

		this.checkExceptions(expected, caught);

	}

}
