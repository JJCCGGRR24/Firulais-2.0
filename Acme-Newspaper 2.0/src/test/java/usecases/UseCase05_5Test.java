
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
public class UseCase05_5Test extends AbstractTest {

	//5.5. An actor who is  authenticated must be able to search for a published newspaper using a single keyword that 
	//must appear somewhere in its title or its description. 

	@Autowired
	private NewspaperService	newspaperService;


	@Test
	public void driver() {
		final Object testingData[][] = {
			//Intentamos buscar un periódico mediante una palabra autenticándonos, y el resultado debe ser positivo.
			{

				"user1", "galleta", null
			},
			//Intentamos buscar un periódico mediante una palabra autenticados como un customer que no existe, y el resultado debe ser negativo.
			{

				"customer80", "galleta", IllegalArgumentException.class
			},
			//Intentamos buscar un periódico mediante una palabra autenticados como un user que no existe, y el resultado debe ser negativo.
			{

				"user96", "galleta", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void template(final String username, final String search, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			this.newspaperService.search(search);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
