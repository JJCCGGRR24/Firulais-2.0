
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.NewspaperService;
import services.TabooService;
import utilities.AbstractTest;
import domain.Taboo;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_1Test extends AbstractTest {

	//SUT

	@Autowired
	private TabooService		tabooService;

	@Autowired
	private NewspaperService	newspaperService;


	//	17. An actor who is authenticated as an administrator must be able to:
	//		1. Manage a list of taboo words.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Inserto una nueva palabra, todo debe ser correcto
				"admin", "taboo", null
			}, {
				//Inserto una palabra que ya esta en la lista de palabras taboo. Debe saltar excepcion
				"user1", "sex", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void template(final String username, final String taboo, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			//listar
			this.tabooService.findAll();
			//crear
			final Taboo t = this.tabooService.create();
			t.setWord(taboo);
			//guardar
			this.tabooService.save(t);
			//borrar
			this.tabooService.delete(t);
			super.unauthenticate();
			this.tabooService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
