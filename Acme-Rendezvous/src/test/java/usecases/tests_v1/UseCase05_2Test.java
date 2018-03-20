
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
public class UseCase05_2Test extends AbstractTest {

	//SUT

	@Autowired
	private RendezvousService	rendezvousService;


	//Create a rendezvous, which he’s implicitly assumed to attend. 
	//	Note that a user may edit his or her rendezvouses as long as they aren’t saved 
	//	them in final mode. Once a rendezvous is saved in final mode, it cannot be edited 
	//	or deleted by the creator.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//creamos un rendezvous como user1 con todos los parametros correctos
				"user1", "name1", "description1", "24/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null

			}, {
				//creamos un rendezvous con la descripción nula, lo cual no podría ser posible ya que solo crean 
				"manager1", "name1", "", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", NullPointerException.class

			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);

	}
	protected void templateCreate(final String username, final String name, final String description, final String moment, final String picture, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//creo rendezvous
			final Rendezvous r = this.rendezvousService.create();
			//introduzco datos al rendezvous
			r.setName(name);
			r.setDescription(description);
			final Date organizationMoment = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(moment);
			r.setMoment(organizationMoment);
			r.setPicture(picture);
			this.rendezvousService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();
	}

}
