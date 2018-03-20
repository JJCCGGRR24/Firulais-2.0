
package usecases.tests_v2;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CategoryService;
import utilities.AbstractTest;
import domain.Category;
import domain.Servicce;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase10Test extends AbstractTest {

	//	10. An actor who is not authenticated must be able to:
	//		1. List the rendezvouses in the system grouped by category

	// System under test ------------------------------------------------------
	@Autowired
	private CategoryService	categoryService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				null, null
			}, {
				"manager11", IllegalArgumentException.class
			}
		};
		System.out.println("Test Caso de Uso 10");
		System.out.println("");
		for (int i = 0; i < testingData.length; i++) {
			System.out.println("Test " + (i + 1) + ":");
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Modifico el nombre del sistema
			final Category c = this.categoryService.findOne(this.getEntityId("Dalqi"));
			System.out.println("Nombre de los servicios de la cateogría " + c.getName() + ":");
			for (final Servicce s : c.getServicces())
				System.out.println("- " + s.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrado correctamente.");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
		}

		this.checkExceptions(expected, caught);

		System.out.println("");
	}
}
