
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
public class PTEUseCase24_1Test extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService	newspaperService;


	//	24. An actor who is authenticated as an administrator must be able to:
	//		1. Display a dashboard with the following information:
	//			- The ratio of public versus private newspapers.
	//			- The average number of articles per private newspapers.
	//			- The average number of articles per public newspapers.
	//			- The ratio of subscribers per private newspaper versus the total 
	//			number of customers.
	//			- The average ratio of private versus public newspapers per publisher.
	//			Non-functional requirements

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Hacemos una llamada a una query como admin
				"admin", null
			}, {
				//Hacemos una llamada a una query como user
				"user1", null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][8]);

	}
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
