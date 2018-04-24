
package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	private TabooService	tabooService;


	//	@Autowired
	//	private NewspaperService	newspaperService;

	//	17. An actor who is authenticated as an administrator must be able to:
	//		1. Manage a list of taboo words.

	@Test
	public void driver() {
		final Object testingDataSave[][] = {
			{
				//1 Inserto una nueva palabra, todo debe ser correcto
				"admin", "taboo", null, "insert"
			},
			{
				//2 Inserto una palabra que ya esta en la lista de palabras taboo. Debe saltar excepcion
				"user1", "sex", IllegalArgumentException.class, "insert"
			},
			{
				//3 Inserto una palabra registrado como customer
				"customer1", "melocoton", IllegalArgumentException.class, "insert"
			},
			{
				//4 Inserto una palabra registrado como user
				"user1", "manzana", IllegalArgumentException.class, "insert"
			},
			{
				//5 Inserto una palabra sin estar logueado
				null, "melon", IllegalArgumentException.class, "insert"
			},
			{
				//6 Elimino una palabra que exista
				"admin", "cialis", null, "delete"
			},
			{
				//7 Elimino una palabra que no exista.No debe dar error.
				"admin", "oblea", null, "delete"
			},
			{
				//8 Modifico una palabra existente 
				"admin", "judia", null, "modify"
			},
			{
				//9 Inserto una palabra nula
				"admin", null, ConstraintViolationException.class, "insert"
			},
			{
				//10 Inserto una palabra demasiado larga
				"admin",
				"nmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjvnmxjkjsadlkjsfjsbkjdsabjv",
				ConstraintViolationException.class, "insert"
			}

		};

		System.out.println("");
		System.out.println("TEST TABOO WORDS");
		for (int i = 0; i < testingDataSave.length; i++) {
			System.out.println("Prueba " + (i + 1));
			this.template((String) testingDataSave[i][0], (String) testingDataSave[i][1], (Class<?>) testingDataSave[i][2], (String) testingDataSave[i][3], i);
			System.out.println("");
		}
		System.out.println("Fin");
		System.out.println("");

	}
	protected void template(final String username, final String taboo, final Class<?> expected, final String s, final int i) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			if (s == "insert") {
				final Taboo t = this.tabooService.create();
				t.setWord(taboo);
				final Taboo t2 = this.tabooService.save(t);
				this.tabooService.flush();
				System.out.println(t2.getWord() + " fue correctamente guardada.");
			} else if (s == "delete") {
				final Taboo t = this.tabooService.create();
				t.setWord("pruebatemplate" + i);
				final Taboo t2 = this.tabooService.save(t);
				this.tabooService.delete(t2);
				this.tabooService.flush();
				System.out.println(taboo + " fue correctamente eliminada.");
			} else if (s == "modify") {
				final Taboo t = this.tabooService.create();
				t.setWord("viagra" + i);
				final Taboo t2 = this.tabooService.save(t);
				t2.setWord("pruebevigra" + i);
				this.tabooService.flush();
				System.out.println("sex fue correctamente modificada a " + t2.getWord());
			}
			super.authenticate(null);
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepción controlada correctamente: " + oops.getClass());
		}

		super.checkExceptions(expected, caught);
	}
}
