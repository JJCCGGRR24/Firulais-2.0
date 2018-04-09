
package usecases;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Chirp;
import services.ChirpService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_5Test extends AbstractTest {

	//SUT

	@Autowired
	private ChirpService chirpService;

	//	16. An actor who is authenticated as a user must be able to:
	//		5. Display a stream with the chirps posted by all of the users that he or she follows.


	//DRIVERS-------------------------------------------------------------------------------

	@Test
	public void driverList() {
		System.out.println("---LIST CHIRPS OF FOLLOWED BY USER---");
		final Object testingData[][] = {
			{

				"user1", null
			}, {

				"user1000", IllegalArgumentException.class
			}, {

				"admin", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateList((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	//TEMPLATES------------------------------------------------------------------------------

	protected void templateList(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			final ArrayList<Chirp> chirps = new ArrayList<>(this.chirpService.getChirpsFromFolloweds());

			for (final Chirp chirp : chirps)
				System.out.println(chirp.getTitle());

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
