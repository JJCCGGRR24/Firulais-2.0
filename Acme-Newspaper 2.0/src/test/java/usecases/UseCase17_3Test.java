
package usecases;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.NewspaperService;
import utilities.AbstractTest;
import domain.Newspaper;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_3Test extends AbstractTest {

	//SUT

	//	@Autowired
	//	private TabooService		tabooService;

	@Autowired
	private NewspaperService	newspaperService;


	//	@Autowired
	//	private ArticleService		articleService;

	//	17. An actor who is authenticated as an administrator must be able to:
	//		3.List the newspapers that contain taboo words.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Accedo a la lista y el tamaño de la lista debe ser 0. Todo correcto.
				"admin", 0, null
			}, {
				//Accedo a la lista pensando que el tamaño es 3 cuando debe ser 0. IlegalArgumentException
				"admin", 3, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void template(final String username, final int numNewspapersTaboo, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			//listar
			final List<Newspaper> newspapers = this.newspaperService.getNewspaperTabooWords();
			Assert.isTrue(newspapers.size() == numNewspapersTaboo);
			super.unauthenticate();
			this.newspaperService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
