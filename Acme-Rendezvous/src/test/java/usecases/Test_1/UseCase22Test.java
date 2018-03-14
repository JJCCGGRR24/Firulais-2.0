
package usecases.Test_1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CommentService;
import services.RendezvousService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase22Test extends AbstractTest {

	//	22. An actor who is authenticated as an administrator must be able to:
	//		1. Display a dashboard with the following information:
	//		   -The average and the standard deviation of the number of questions per ren-dezvous.
	//		   -The average and the standard deviation of the number of answers to the questions per rendezvous.
	//		   -The average and the standard deviation of replies per comment.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private CommentService		commentService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"admin", null
			}, {
				"manager1", IllegalArgumentException.class
			//Un manager 

			}, {
				"user1", IllegalArgumentException.class
			//Un user
			}, {
				null, IllegalArgumentException.class
			//Un anónimo
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
		System.out.println("");
		System.out.println("TODO OK");
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Muestro todos las Queries del sistema
			final Double[] query1 = this.rendezvousService.queryA1();
			System.out.println("Query1: (" + query1[0] + ", " + query1[1] + ")");
			System.out.println("-----------------------------");
			final Double[] query2 = this.rendezvousService.queryA2();
			System.out.println("Query 2: (" + query2[0] + ", " + query2[1] + ")");
			System.out.println("-----------------------------");
			final Double[] query3 = this.commentService.queryA3();
			System.out.println("Query 3: (" + query3[0] + ", " + query3[1] + ")");
			System.out.println("-----------------------------");

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrado correctamente.");
			System.out.println("");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepción controlada en test negativo: " + caught);
		}

		this.checkExceptions(expected, caught);

	}

}
