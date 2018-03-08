
package usecases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ManagerService;
import utilities.AbstractTest;
import domain.Manager;
import forms.RegisterManagerForm;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase03Test extends AbstractTest {

	//	3. An actor who is not authenticated must be able to:
	//		1. Register to the system as a manager.

	// System under test ------------------------------------------------------
	@Autowired
	private ManagerService	managerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				//Registramos un manager correctamente
				"manager111", "Usuario nuevo", "usuario", "kkxkd@gmail.com", "123456789", "c/ falsa 123", "contraseña", "123456789", null
			}, {
				//Registramos un manager con un email incorrecto.
				"manager222", "Usuario nuevo 2", "usuario2", "kkxkdkkd.com", "123456789", "c/ falsa 123", "contraseña", "123456789", ConstraintViolationException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(Class<?>) testingData[i][8]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String name, final String surname, final String email, final String phone, final String postalAddress, final String password, final String vat, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Creo el manager
			final RegisterManagerForm c = new RegisterManagerForm();

			// Usaremos el formato de fecha que necesitemos
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			final String dateInString = "1994-09-15";
			Date fecha = null;
			try {
				fecha = sdf.parse(dateInString);
			} catch (final ParseException e) {
				e.printStackTrace();
			}

			//Meto los datos del manager
			c.setEmail(email);
			c.setName(name);
			c.setPhone(phone);
			c.setSurname(surname);
			c.setBirthdate(fecha);
			c.setPostalAddress(postalAddress);
			c.setVat(vat);
			c.setCheck(true);
			c.setPassword(password);
			c.setPasswordConfirm(password);
			c.setUsername(username);

			final Manager m = this.managerService.reconstruct(c);

			//Guardo el customer
			this.managerService.save(m);

			this.managerService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
