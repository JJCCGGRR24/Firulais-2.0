
package usecases.Test_2;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Servicce;
import services.CategoryService;
import services.RendezvousService;
import services.ServicceService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase11_2Test extends AbstractTest {

	//	11. An actor who is authenticated as an administrator must be able to:
	//		1. Display a dashboard with the following information:
	//	 		The average number of categories per rendezvous.
	//			The average ratio of services in each category.
	//			The average, the minimum, the maximum, and the standard deviation of services requested per rendezvous.
	//			The top-selling services.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ServicceService		servicceService;

	@Autowired
	private CategoryService		categoryService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"admin", null
			}, {
				"manager1", IllegalArgumentException.class //Un manager 

			}, {
				"user1", IllegalArgumentException.class //Un user
			}, {
				null, IllegalArgumentException.class //Un anonimo
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos como MANAGER
			this.authenticate(username);

			//Muestro todos las Queries del sistema
			System.out.println("-----------------------------");
			final Double query1 = this.rendezvousService.queryNewB1();
			System.out.println("Query1: " + query1);
			System.out.println("-----------------------------");
			final Double query2 = this.categoryService.queryNewB2();
			System.out.println("Query 2: " + query2);
			System.out.println("-----------------------------");
			final Double[] query3 = this.categoryService.queryNewB3();
			System.out.println("Query 3: ");
			for (final Double double1 : query3)
				System.out.println(double1);
			System.out.println("-----------------------------");
			final ArrayList<Servicce> list = new ArrayList<>(this.servicceService.queryNewC1B4());
			System.out.println("Query 4: ");
			for (final Servicce servicce : list)
				System.out.println(servicce.getName());
			System.out.println("-----------------------------");

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
		}

		this.checkExceptions(expected, caught);

	}

}
