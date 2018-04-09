
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.UserService;
import utilities.AbstractTest;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase21_1Test extends AbstractTest {

	//SUT

	@Autowired
	private UserService	userService;


	//	21. An actor who is not authenticated must be able to:
	//		1. Register to the system as a customer.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//un registro normal
				"nuevouser", null
			}, {
				//registro con usuario repetido
				"user1", DataIntegrityViolationException.class
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
			final User user = this.userService.create();
			user.getUserAccount().setUsername(username);
			user.getUserAccount().setPassword(username);
			user.setEmail("a@a.a");
			user.setName("a");
			user.setPhone("666666666");
			user.setPostalAddress("a");
			user.setSurname("a");
			System.out.println(this.userService.save(user).getUserAccount());
			this.userService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepción capturada");
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
