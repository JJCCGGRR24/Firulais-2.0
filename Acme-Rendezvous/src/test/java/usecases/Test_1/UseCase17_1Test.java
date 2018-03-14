
package usecases.Test_1;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Announcement;
import services.AnnouncementService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_1Test extends AbstractTest {

	//	17. An actor who is authenticated as an administrator must be able to:
	//		1. Remove an announcement that he or she thinks is inappropriate.

	// System under test ------------------------------------------------------
	@Autowired
	private AnnouncementService announcementService;

	// Tests ------------------------------------------------------------------


	@Test
	public void driver() {

		final Object testingData[][] = {
			{
				"admin", "announcement1", null
			}, {
				"manager1", "announcement1", IllegalArgumentException.class //Un manager 
			}, {
				"user1", "announcement1", IllegalArgumentException.class //Un user
			}, {
				null, "announcement1", IllegalArgumentException.class //Un user
			}, {
				"admin", "announcement10000", java.lang.AssertionError.class //Un announcement que no exite
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String announcement, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos
			this.authenticate(username);

			//Buscamos el announcement
			final int announcementId = this.getEntityId(announcement);
			final Announcement announcement2 = this.announcementService.findOne(announcementId);

			//Borramos el annoucement
			this.announcementService.delete(announcement2);

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
		}

		this.checkExceptions(expected, caught);

		System.out.println("-----------------------------");
	}

}
