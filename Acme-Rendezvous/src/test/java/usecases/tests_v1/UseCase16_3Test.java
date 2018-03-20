
package usecases.tests_v1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Announcement;
import domain.Rendezvous;
import services.AnnouncementService;
import services.RendezvousService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase16_3Test extends AbstractTest {

	//	16. An actor who is authenticated as a user must be able to:
	//		3. Create an announcement regarding one of the rendezvouses
	//			that he or she’s creat-ed previously.

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private AnnouncementService	announcementService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"user1", "rendezvous1", "Titulo Prueba 1", "Description prueba 1", null //Un user
			}, {
				"user1", "rendezvous4", "Titulo Prueba 1", "Description prueba 1", java.lang.IllegalArgumentException.class //Un user intenta crear un announcement en rendezvous que no es suyo
			}, {
				null, "rendezvous1", "Titulo Prueba 1", "Description prueba 1", java.lang.IllegalArgumentException.class //Un anonimo intenta crear un announcement
			}, {
				"manager1", "rendezvous1", "Titulo Prueba 1", "Description prueba 1", java.lang.ClassCastException.class//Un manager intenta crear un announcement en un rendezvous
			}, {
				"user1", "rendezvous100", "Titulo Prueba 1", "Description prueba 1", java.lang.AssertionError.class //Crear un announcement en un rendezvous que no existe
			}, {
				"user1", "rendezvous1", null, "Description prueba 1", javax.validation.ConstraintViolationException.class //Crear un announcement en un rendezvous con un campo en blanco
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String rendezvous, final String title, final String description, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Creamos un annoucement
			final int rendezvousId = this.getEntityId(rendezvous);
			final Rendezvous rendezvous1 = this.rendezvousService.findOne(rendezvousId);
			final Announcement announcement = this.announcementService.create(rendezvous1);
			announcement.setTitle(title);
			announcement.setDescription(description);

			//Guardamos el annoucement
			final Announcement saved = this.announcementService.save(announcement);
			Assert.isTrue(this.announcementService.findAll().contains(saved));

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
