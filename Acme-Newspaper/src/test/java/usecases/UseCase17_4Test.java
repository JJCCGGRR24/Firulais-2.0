
package usecases;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

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
public class UseCase17_4Test extends AbstractTest {

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
	//		4.List the chirps that contain taboo words.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Accedo a la lista y el tamaño de la lista debe ser 1. Todo correcto.
				"admin", 1, null
			}, {
				//Accedo a la lista pensando que el tamaño es 3 cuando debe ser 1. IlegalArgumentException
				"admin", 3, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void template(final String username, final int numChirpssTaboo, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			//listar
			final List<Chirp> chirps = this.chirpService.getChirpsTabooWords();
			Assert.isTrue(chirps.size() == numChirpssTaboo);
			super.unauthenticate();
			this.chirpService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
