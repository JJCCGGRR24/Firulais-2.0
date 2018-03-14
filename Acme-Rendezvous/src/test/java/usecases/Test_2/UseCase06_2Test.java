
package usecases.Test_2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import services.ServicceService;
import utilities.AbstractTest;
import domain.Category;
import domain.Rendezvous;
import domain.Servicce;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase06_2Test extends AbstractTest {

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
				//accedemos a crear categoria con admin, no debe de haber problema pues es el unico que puede
				//crear categorias
				"user1", "name1", "description1", "11/12/2018 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null
			}, {
				//accedemos a crear categoria con user, debería de saltar la excepción pues esto es ilegal
				"user1", "name1", "description1", "11/12/2017 20:00", "https://forum.linkinpark.com/t/new-linkin-park-logo-what-do-you-think/31363", null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], super.getEntityId((String) testingData[i][4]), super.getEntityId((String) testingData[i][4]), (Class<?>) testingData[i][5]);

	}

	protected void template(final String username, final String name, final String description, final Date moment, final String picture, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//creo rendezvous
			final Rendezvous r = this.rendezvousService.create();

			//introduzco datos a la categoria
			r.setName(name);
			r.setDescription(description);
			r.setMoment(moment);
			r.setPicture(picture);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}



}
