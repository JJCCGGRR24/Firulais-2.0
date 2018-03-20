
package usecases.tests_v1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CommentService;
import services.RendezvousService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_6Test extends AbstractTest {

	//SUT

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private CommentService		commentService;


	//	Comment on the rendezvouses that he or she has RSVPd.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Vamos a comentar en un rendezvous que hemos creado, que tambien pertenece a la lista de 
				//rendezvouses a la cual va a asistir, con lo que deberia permitirnos
				"user1", "rendezvous1", "comment1", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null

			}, {
				//Vamos a comentar en un rendezvous el cual no vamos a asistir y no nos deberia
				//permitir hacerlo
				"user1", "rendezvous5", "commment 1", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", IllegalArgumentException.class

			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);

	}
	protected void template(final String username, final int rendezvousId, final String comment, final String picture, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//busco el rendezvous
			final Rendezvous r = this.rendezvousService.findOne(rendezvousId);

			//Creo el comentario
			final Comment commentario = this.commentService.create(r);
			commentario.setPicture(picture);
			commentario.setText(comment);

			//			guardo el comentario
			final List<Comment> comments = r.getComments();
			comments.add(commentario);

			this.commentService.save(commentario);
			System.out.println(r.getComments());

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();
	}

}
