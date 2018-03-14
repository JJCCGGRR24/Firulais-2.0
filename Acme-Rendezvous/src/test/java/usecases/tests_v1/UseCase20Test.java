
package usecases.tests_v1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import utilities.AbstractTest;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase20Test extends AbstractTest {

	//	20. An actor who is not authenticated must be able to:
	//		1. Display information about the users who have RSVPd a rendezvous,
	//		   which, in turn, must show their answers to the questions that the 
	//		   creator has registered.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				108, null
			//Id de un rendezvous
			}, {
				-4, NullPointerException.class

			//ID de un rendezvous inexistente
			}
		};
		for (int i = 0; i < testingData.length; i++) {
			this.template((int) testingData[i][0], (Class<?>) testingData[i][1]);
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
		System.out.println("");
		System.out.println("TODO OK");
	}
	// Ancillary methods ------------------------------------------------------

	protected void template(final int id, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			final Rendezvous r = this.rendezvousService.findOne(id);
			System.out.println("Usuarios apuntados a: " + r.getName());
			for (final User u : r.getUsers()) {
				System.out.println("   + " + u.getUserAccount().getUsername());
				for (final Question q : r.getQuestions())
					for (final Answer a : q.getAnswers())
						if (a.getUser().equals(u)) {
							System.out.println("      P:" + q.getText());
							System.out.println("      R:" + a.getText());
							System.out.println("");
						}
				System.out.println("");
			}
			System.out.println("Mostrado corectamente");
			System.out.println("");

		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepcion controlada en test negativo: " + caught);
		}

		this.checkExceptions(expected, caught);

	}
}
