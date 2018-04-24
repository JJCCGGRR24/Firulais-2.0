
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.NewspaperService;
import utilities.AbstractTest;
import domain.Newspaper;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase06_1Test extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService	newspaperService;


	//	6. An actor who is authenticated as a user must be able to:
	//		1. Create a newspaper. A user who has created a newspaper is commonly referred to
	//		as a publisher.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//comprobaremos que un user puede crear un newspaper correctamente
				"user1", "titleExample", "descriptionExample", "https://www.youtube.com/watch?v=cwZ0NHyz9n8&list=RDMMLAMiX5EEbFU&index=19", null
			}, {
				//comprobaremos que un user al dejar el title en blanco no deja guardar el newspaper
				"user1", "", "descriptionExample", "https://www.youtube.com/watch?v=cwZ0NHyz9n8&list=RDMMLAMiX5EEbFU&index=19", javax.validation.ConstraintViolationException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);

	}
	protected void template(final String username, final String title, final String description, final String picture, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final Newspaper news = this.newspaperService.create();
			news.setTitle(title);
			news.setDescription(description);
			news.setPicture(picture);
			this.newspaperService.save(news);
			this.newspaperService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
