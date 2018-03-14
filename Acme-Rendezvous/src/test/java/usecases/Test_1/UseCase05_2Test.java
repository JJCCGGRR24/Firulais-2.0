
package usecases.Test_1;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import services.ServicceService;
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

	@Autowired
	private ServicceService		servicceService;


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

			}, {
				//editamos un rendezvous nuestro, lo cual no deberia haber problema
				"user1", "rendezvous1", "name1", "description1", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null
			}, {
				//editamos un rendezvous que no es nuestro, lo cual no deberia dejarnos
				"user1", "rendezvous2", "name1", "description1", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", IllegalArgumentException.class
			}
		//								, {
		//				//borramos un rendezvous que es nuestro, lo cual no deberia haber problema
		//				"user1", "rendezvous1", null
		//			}, {
		//				"user1", "rendezvous5", IllegalArgumentException.class
		//			}

		};

		for (int i = 0; i < 2; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);

		for (int i = 2; i < 4; i++)
			this.templateEdit((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);

		//		for (int i = 4; i < testingData.length; i++)
		//			this.templateDelete((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], super.getEntityId((String) testingData[i][4]), super.getEntityId((String) testingData[i][4]), (Class<?>) testingData[i][5]);

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

}
