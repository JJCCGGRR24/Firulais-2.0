
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.UserAccount;
import services.UserService;
import utilities.AbstractTest;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase4_1Test extends AbstractTest {

	// 4.1. An actor who is not authenticated must be able to register to the system as a user. 

	@Autowired
	private UserService	userService;


	@Test
	public void driver() {
		final Object testingData[][] = {

			//Intentamos registrar un usuario y el resultado debe ser positivo. 
			{

				"Juan", "Rodríguez", "churrasquito@gmail.com", "646597809", "Churrasquito", "pimpo", "El Rubio", null
			},
			//Intentamos registrar un usuario, y el resultado debe ser negativo ya que el usuario ya existe.
			{

				"Juana Patricia", "Escobar", "lapatri@gmail.com", "646597807", "user1", "Labrador", "Cuartel", DataIntegrityViolationException.class
			},
			//Intentamos registrar un usuario y el resultado debe ser negativo ya que la contraseña excede el tamaño máximo.
			{

				"Juan", "Rodríguez", "churrasquito@gmail.com", "646597809", "nellyfurtado", "use62626262651ñlkohjvgfxzxfgcvhbnr11", "El Rubio", DataIntegrityViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Class<?>) testingData[i][7]);

	}
	protected void template(final String name, final String surname, final String email, final String phone, final String user, final String password, final String postalAddress, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			//Creo el user
			final User u = this.userService.create();

			//Creo el userAccount
			final UserAccount userAccount = u.getUserAccount();
			userAccount.setPassword(password);
			userAccount.setUsername(user);

			//Meto los datos del user

			u.setUserAccount(userAccount);
			u.setName(name);
			u.setSurname(surname);
			u.setPostalAddress(postalAddress);
			u.setPhone(phone);
			u.setEmail(email);

			//Guardo el customer
			this.userService.save(u);

			this.userService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
