
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.NewspaperService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase07_2Test extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService	newspaperService;


	//	7. An actor who is authenticated as an administrator must be able to:
	//		2. Remove a newspaper that he or she thinks is inappropriate. Removing a newspaper
	//		implies removing all of the articles of which it is composed.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//eliminamos como admin un periodico
				"admin", null
			}, {
				//eliminamos como user un periodico publicado
				"user1", IllegalArgumentException.class
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
			this.newspaperService.delete(this.newspaperService.findOne(this.getEntityId("newspaper1")));
			super.authenticate(null);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
