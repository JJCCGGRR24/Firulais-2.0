
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ArticleService;
import services.ChirpService;
import services.NewspaperService;
import services.TabooService;
import utilities.AbstractTest;
import domain.Chirp;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_5Test extends AbstractTest {

	//SUT

	@Autowired
	private TabooService		tabooService;

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private ChirpService		chirpService;


	//	17. An actor who is authenticated as an administrator must be able to:
	//		5.Remove a chirp that he or she thinks is inappropriate.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Intento borrar un chirp siendo administrador. Todo correcto.
				"admin", null
			}, {
				//Intento borrar un chirp siendo usuario. IlegalArgumentException
				"user1", IndexOutOfBoundsException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Chirp chirp = this.chirpService.getChirpsTabooWords().get(0);
			System.out.println(chirp);
			this.chirpService.delete(chirp);

			super.unauthenticate();
			this.chirpService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
