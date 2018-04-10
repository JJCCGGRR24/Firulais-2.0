
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
public class UseCase04_2Test extends AbstractTest {

	//4.2. An actor who is not authenticated must be able to list the newspapers that are published and browse their articles. 

	@Autowired
	private NewspaperService	newspaperService;


	@Test
	public void driver() {
		final Object testingData[][] = {
			//Listamos los periódicos publicados sin autenticarnos, y el resultado debe ser positivo.
			{

				null, null
			},
			//Intentamos listar los periódicos publicados autenticados como customer, y el resultado debe ser negativo.
			{

				" customer80", IllegalArgumentException.class
			},
			//Intentamos listar los periódicos publicados autenticados como user, y el resultado debe ser negativo.
			{

				"user96", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			this.newspaperService.getPublishedNewspapers();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
