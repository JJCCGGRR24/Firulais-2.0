
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Announcement;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Tests ------------------------------------------------------------------

	@Test
	public void getAnnouncementByRendezvousId() {

		final Integer rendezvousId = super.getEntityId("rendezvous3");
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		final Collection<Announcement> announcements = this.announcementService.getAnnouncementsByRendezvousId(rendezvousId);
		System.out.println("---- Test getAnnouncementByRendezvousId ----");
		Assert.isTrue(announcements != null);
		for (final Announcement a : announcements) {
			Assert.isTrue(a.getRendezvous().equals(rendezvous));
			System.out.println(a.getTitle() + " " + a.getDescription() + " " + a.getMoment());
		}
	}
}
