
package usecases.tests_v1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.QuestionService;
import services.RendezvousService;
import utilities.AbstractTest;
import domain.Question;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase21_1Test extends AbstractTest {

	//	21. An actor who is authenticated as a user must be able to:
	//		1. Manage the questions that are associated with a rendezvous 
	//		   that he or she’s created previously.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private QuestionService		questionService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"user4", null
			//Un user
			}, {
				"manager1", IllegalArgumentException.class
			//Un manager
			}
		};
		for (int i = 0; i < testingData.length; i++) {
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
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
			final Rendezvous r = this.rendezvousService.findOne(this.getEntityId("rendezvous4"));
			Question q = this.questionService.create(r);
			q.setText("¿De que color es el caballo blanco de Santiago?");
			q = this.questionService.save(q);
			//			this.questionService.flush();
			System.out.println("Nueva pregunta para el rendezvous " + q.getRendezvous().getName());
			System.out.println(q.getText());
			System.out.println("");

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Pregunta añadida correctamente con id: " + q.getId());
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("Excepcion controlada en test negativo: " + caught);
		}

		this.checkExceptions(expected, caught);

	}
}
