
package usecases.tests_v1;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import utilities.AbstractTest;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_3Test extends AbstractTest {

	//SUT

	@Autowired
	private RendezvousService	rendezvousService;


	//	Update or delete the rendezvouses that he or she’s created. D
	//	eletion is virtual, that is: the information is not removed from the database, 
	//	but the rendezvous cannot

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//editamos un rendezvous nuestro, lo cual no deberia haber problema
				"user1", "rendezvous1", "name1", "description1", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null
			}, {
				//editamos un rendezvous que no es nuestro, lo cual no deberia dejarnos
				"user1", "rendezvous2", "name1", "description1", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", IllegalArgumentException.class
			}, {	//borramos un rendezvous que es nuestro, lo cual no deberia haber problema							, {
				"user3", "rendezvous6", null
			}, {
				//intentamos borrar un rendezvous nuestro pero que esta en modo final, lo cual no debe
				//ser posible
				"user4", "rendezvous5", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < 2; i++)
			this.templateEdit((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);

		for (int i = 2; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);

	}
	protected void templateEdit(final String username, final int rendezvousId, final String name, final String description, final String moment, final String picture, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//busco el rendezvous
			final Rendezvous r = this.rendezvousService.findOne(rendezvousId);

			//modifico el rendezvous
			r.setName(name);
			r.setDescription(description);
			final Date organizationMoment = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(moment);
			r.setMoment(organizationMoment);
			r.setPicture(picture);
			this.rendezvousService.update(r);
			this.rendezvousService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}

	public void templateDelete(final String username, final int rendezvousId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//busco el rendezvous
			final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
			r.setDeleted(true);
			this.rendezvousService.update(r);

			this.rendezvousService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
