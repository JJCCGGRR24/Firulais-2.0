
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.UserService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_2Test extends AbstractTest {

	//SUT

	@Autowired
	private UserService	userService;


	//	16. An actor who is authenticated as a user must be able to:
	//		2. Follow or unfollow another user.

	//DRIVERS-------------------------------------------------------------------------------

	@Test
	public void driverFollow() {
		System.out.println("---FOLLOW A USER---");
		final Object testingData[][] = {
			{

				"user2", "user3", null
			}, {
				"user1", "user1000", NumberFormatException.class
			}, {
				"user1", "admin", java.lang.NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateFollow((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	@Test
	public void driverUnfollow() {
		System.out.println("---UNFOLLOW A USER---");
		final Object testingData[][] = {
			{

				"user1", "user3", null
			}, {
				"user1", "user1000", NumberFormatException.class
			}, {
				"user1", "admin", java.lang.NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateUnfollow((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	//TEMPLATES------------------------------------------------------------------------------

	protected void templateFollow(final String username, final String userToFollow, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			final int userId = this.getEntityId(userToFollow);
			this.userService.follow(userId);

			System.out.println("Operation successful!");

		} catch (final Throwable oops) {
			System.out.println("----");
			caught = oops.getClass();
			System.out.println("Expected: " + caught);
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}

	protected void templateUnfollow(final String username, final String userToUnfollow, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(username);

			final int userId = this.getEntityId(userToUnfollow);
			this.userService.unfollow(userId);

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
