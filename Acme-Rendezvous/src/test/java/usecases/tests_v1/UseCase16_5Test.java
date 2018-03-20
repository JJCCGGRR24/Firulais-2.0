
package usecases.tests_v1;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Announcement;
import domain.Rendezvous;
import services.ActorService;
import services.RendezvousService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_5Test extends AbstractTest {

	//	16. An actor who is authenticated as a user must be able to:
	//		5. Display a stream of announcements that have been posted to the rendezvouses
	//		that he or she’s RSVPd.
	//		The announcements must be listed chronologically in descending order.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"user1", "rendezvous1", null //Un user
			}, {
				"user2", "rendezvous1", null //Un user
			}, {
				"user3", "rendezvous1", IllegalArgumentException.class //Un user que no va asistir
			}, {
				null, "rendezvous1", java.lang.AssertionError.class //Un anonimo
			}, {
				"manager1", "rendezvous1", java.lang.IllegalArgumentException.class //Un manager
			}, {
				"user1", "rendezvous100", java.lang.AssertionError.class //rendezvous que no existe
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String rendezvous, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);
			final int actorId = this.getEntityId(username);
			final Actor principal = this.actorService.findOne(actorId);

			//Buscamos los rendezvouses
			final int rendezvousId = this.getEntityId(rendezvous);
			final Rendezvous rendezvous1 = this.rendezvousService.findOne(rendezvousId);

			final ArrayList<Announcement> announcements = new ArrayList<>(rendezvous1.getAnnouncements());
			for (final Announcement announcement : announcements) {
				Assert.isTrue(announcement.getRendezvous().getUsers().contains(principal));
				System.out.println(announcement.getTitle());
			}

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-----------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-----------------------------");
		}

		this.checkExceptions(expected, caught);

	}

}
