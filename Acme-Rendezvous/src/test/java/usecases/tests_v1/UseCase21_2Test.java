
package usecases.tests_v1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AnswerService;
import services.QuestionService;
import utilities.AbstractTest;
import domain.Answer;
import domain.Question;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase21_2Test extends AbstractTest {

	//	21. An actor who is authenticated as a user must be able to:
	//		2. Answer the questions that are associated with a rendezvous 
	//		   that he or she’s RSVPing now.

	// System under test ------------------------------------------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private QuestionService	questionService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"user1", null
			//Un user
			}, {
				"manager1", ClassCastException.class
			//Un manager
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

			final Question q = this.questionService.findOne(this.getEntityId("question2"));
			Answer a = this.answerService.create(q);
			a.setText("Leonardo Dicaprio");
			a = this.answerService.save(a);
			this.answerService.flush();
			System.out.println("P: " + a.getQuestion().getText());
			System.out.println("R: " + a.getText());
			System.out.println("");

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Pregunta respondida correctamente con id: " + a.getId());
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println("");
			System.out.println("Excepcion controlada en test negativo: " + caught);
		}

		this.checkExceptions(expected, caught);

	}
}
