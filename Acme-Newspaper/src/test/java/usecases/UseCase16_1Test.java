
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ChirpService;
import utilities.AbstractTest;
import domain.Chirp;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_1Test extends AbstractTest {

	//SUT

	@Autowired
	private ChirpService	chirpService;


	//	16. An actor who is authenticated as a user must be able to:
	//		1. Post a chirp. Chirps may not be changed or deleted once they are posted.paper.

	//DRIVERS-------------------------------------------------------------------------------

	@Test
	public void driverPost() {
		System.out.println("---POST A CHIRP BY USER---");
		final Object testingData[][] = {
			{

				"user1", "titleExample", "descriptionExample", null
			}, {
				//comprobaremos que un user al dejar el title en blanco no deja guardar el newspaper
				"user1", "", "descriptionExample", javax.validation.ConstraintViolationException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templatePost((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

	}

	@Test
	public void driverDelete() {
		System.out.println("---DELETE A CHIRP BY USER---");
		final Object testingData[][] = {
			{
				"user1", "chirp1_1", java.lang.IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	//TEMPLATES------------------------------------------------------------------------------

	protected void templatePost(final String username, final String title, final String description, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			final Chirp chirp = this.chirpService.create();
			chirp.setTitle(title);
			chirp.setDescription(description);

			this.chirpService.save(chirp);
			this.chirpService.flush();

			System.out.println("Operation successful!");

		} catch (final Throwable oops) {
			System.out.println("----");
			caught = oops.getClass();
			System.out.println("Expected: " + caught);
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}

	protected void templateDelete(final String username, final String chirp, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			final int chirpId = this.getEntityId(chirp);
			final Chirp chirp2 = this.chirpService.findOne(chirpId);

			this.chirpService.delete(chirp2);
			this.chirpService.flush();

			System.out.println("Operation successful!");

		} catch (final Throwable oops) {
			System.out.println("----");
			caught = oops.getClass();
			System.out.println("Expected: " + caught);
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
