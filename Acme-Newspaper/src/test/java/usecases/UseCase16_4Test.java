
package usecases;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.User;
import services.UserService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_4Test extends AbstractTest {

	//SUT

	@Autowired
	private UserService userService;

	//	16. An actor who is authenticated as a user must be able to:
	//		3. List the users who follow him or her.


	//DRIVERS-------------------------------------------------------------------------------

	@Test
	public void driverList() {
		System.out.println("---LIST FOLLOWED BY USER---");
		final Object testingData[][] = {
			{

				"user1", null
			}, {

				"admin", java.lang.ClassCastException.class
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

			final ArrayList<User> users = new ArrayList<>(this.userService.getMyFollowers());

			for (final User user : users)
				System.out.println(user.getName());

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
