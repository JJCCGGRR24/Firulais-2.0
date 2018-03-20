
package usecases.tests_v1;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Rendezvous;
import services.RendezvousService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_2Test extends AbstractTest {

	//	17. An actor who is authenticated as an administrator must be able to:
	//		2. Display a dashboard with the following information:
	//			The average and the standard deviation of announcements per rendezvous.
	//			The rendezvouses that whose number of announcements is above 75% the average number of announcements per rendezvous.
	//			The rendezvouses that are linked to a number of rendezvouses that is great-er than the average plus 10%.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;


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
			final Double[] query1 = this.rendezvousService.queryB1();
			System.out.println("Query1: ");
			for (final Double double1 : query1)
				System.out.println(double1);
			System.out.println("-----------------------------");
			final Collection<Rendezvous> query2 = this.rendezvousService.queryB2();
			System.out.println("Query 2: ");
			for (final Rendezvous rendezvous : query2)
				System.out.println(rendezvous.getName());
			System.out.println("-----------------------------");
			final ArrayList<Rendezvous> query3 = new ArrayList<>(this.rendezvousService.queryB3());
			System.out.println("Query 3: ");
			for (final Rendezvous rendezvous : query3)
				System.out.println(rendezvous.getName());
			System.out.println("-----------------------------");

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-----------------------------");
		}

		this.checkExceptions(expected, caught);

	}

}
