
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AdministratorService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_6Test extends AbstractTest {

	//SUT

	@Autowired
	private AdministratorService	administratorService;


	//	17. An actor who is authenticated as an administrator must be able to:
	//		6. Display a dashboard with the following information:
	//			- The average number of follow-ups per article.
	//			- The average number of follow-ups per article up to one week 
	//			after the corresponding newspaper’s been published.
	//			- The average number of follow-ups per article up to two weeks 
	//			after the corresponding newspaper’s been published.
	//			- The average and the standard deviation of the number of chirps per user.
	//			- The ratio of users who have posted above 75% the average number 
	//			of chirps per user.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Hacemos una llamada a una query como admin
				"admin", null
			}, {
				//Hacemos una llamada a una query como user
				"user1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
			System.out.println("-------------------");
		}
	}
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			System.out.println("Llamada a la query de nivel B");
			System.out.println(this.administratorService.queryB1());
			System.out.println("Todo correcto");
			super.authenticate(null);
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepcion controlada");
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
