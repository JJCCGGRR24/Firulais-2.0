
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Newspaper;
import services.NewspaperService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase23_1TestJulian extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService newspaperService;


	//DRIVERS-------------------------------------------------------------------------------

	@Test
	public void driver() {
		System.out.println("---PRIVATE OR PUBLIC NEWSPAPER BY USER---");
		final Object testingData[][] = {
			{

				"user3", "newspaper3", null
			}, {

				"user2", "newspaper1_3", java.lang.AssertionError.class
			}, {

				"user1", "newspaper1", java.lang.IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	//TEMPLATES------------------------------------------------------------------------------

	protected void template(final String username, final String newspaper, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			//Periodico Privado
			final int newspaperId = this.getEntityId(newspaper);
			final Newspaper newspaper2 = this.newspaperService.findOne(newspaperId);

			newspaper2.setDeprived(false);
			this.newspaperService.save(newspaper2);

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
