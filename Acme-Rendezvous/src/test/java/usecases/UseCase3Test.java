
package usecases;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.UserAccount;
import services.ManagerService;
import utilities.AbstractTest;
import domain.Manager;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase3Test extends AbstractTest {

	//	3. An actor who is not authenticated must be able to:
	//		1. Register to the system as a manager.

	// System under test ------------------------------------------------------
	@Autowired
	private ManagerService	managerService;


	// Tests ------------------------------------------------------------------

	@SuppressWarnings("deprecation")
	@Test
	public void driver() {
		final Date fechaMayorEdad = new Date();
		fechaMayorEdad.setDate(10);
		fechaMayorEdad.setMonth(11);
		fechaMayorEdad.setYear(1993);

		final Object testingData[][] = {
			{
				//Registramos un manager correctamente
				"manager111", "Usuario nuevo", "usuario", "kkxkd@gmail.com", "123456789", "c/ falsa 123", "contraseña", fechaMayorEdad, "123456789", null
			}
		//			, {
		//				//Registramos un manager con la de nacimiento actual.
		//				"manager222", "Usuario nuevo 2", "usuario2", "kkxkdkkd@gmail.com", "123456789", "c/ falsa 123", "contraseña", new Date(), "123456789", DataIntegrityViolationException.class
		//			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Date) testingData[i][7],
				(String) testingData[i][8], (Class<?>) testingData[i][9]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String name, final String surname, final String email, final String phone, final String postalAddress, final String password, final Date birthdate, final String vat, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Creo el manager
			final Manager c = this.managerService.create();
			System.out.println("creo");

			//Creo el userAccount
			final UserAccount userAccount = c.getUserAccount();
			userAccount.setPassword(password);
			userAccount.setUsername(username);

			System.out.println("creo useracoun");

			//Meto los datos del customer
			c.setEmail(email);
			c.setName(name);
			c.setPhone(phone);
			c.setSurname(surname);
			c.setBirthdate(birthdate);
			c.setPostalAddress(postalAddress);
			c.setVat(vat);
			c.setUserAccount(userAccount);

			System.out.println(c.getEmail());
			System.out.println(c.getName());
			System.out.println(c.getPhone());
			System.out.println(c.getPostalAddress());
			System.out.println(c.getSurname());
			System.out.println(c.getVat());
			System.out.println(c.getBirthdate().getYear());
			System.out.println(c.getServicces());

			//Guardo el customer
			this.managerService.save(c);

			System.out.println("guardo");

			this.managerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
