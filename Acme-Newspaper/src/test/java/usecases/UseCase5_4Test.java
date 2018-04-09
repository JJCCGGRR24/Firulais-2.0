
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ArticleService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase5_4Test extends AbstractTest {

	//5.4. An actor who is  authenticated must be able to search for a published article using a single key word
	//that must appear somewhere in its title, summary, or body.

	@Autowired
	private ArticleService	articleService;


	@Test
	public void driver() {
		final Object testingData[][] = {
			//Intentamos buscar un artículo mediante una palabra autenticándonos, y el resultado debe ser positivo.
			{

				"user1", "galleta", null
			},
			//Intentamos buscar un artículo mediante una palabra autenticados como un customer que no existe, y el resultado debe ser negativo.
			{

				" customer80", "galleta", IllegalArgumentException.class
			},
			//Intentamos buscar un artículo mediante una palabra autenticados como un user que no existe, y el resultado debe ser negativo.
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
			this.articleService.search(search);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
