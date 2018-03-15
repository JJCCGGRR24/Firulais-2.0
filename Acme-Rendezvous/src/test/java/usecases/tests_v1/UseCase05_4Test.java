
package usecases.tests_v1;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import services.RendezvousService;
import services.UserService;
import utilities.AbstractTest;
import domain.Rendezvous;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_4Test extends AbstractTest {

	//SUT

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;

	@Autowired
	private LoginService		loginService;


	//	RSVP a rendezvous or cancel it. “RSVP” is a French term that means 
	//	“Réservez, s’il vous plaît”; it’s commonly used in the anglo-saxon world to mean “
	//	I will attend this rendezvous”; it’s pronounced as “/ri:’serv/”, “/ri:’serv’silvu’ple/”, 
	//	or “ɑːresviːˈpiː”.
	//	When a user RSVPs a rendezvous, he or she is assumed to attend it.
	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//como user1 voy a asistir al rendezvous3 con lo que no deberia de haber problema
				"user1", "rendezvous3", null

			}, {
				//como user1 voy a asistir al rendezvous5 que esta con deleted = true
				//(lo que quiere decir que ha sido borrado virtualmente)
				//con lo que no deberia de permitirnoslo
				"user1", "rendezvous5", IllegalArgumentException.class

			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRSVP((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);

	}
	protected void templateRSVP(final String username, final int rendezvousId, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//busco el rendezvous
			final Rendezvous r = this.rendezvousService.findOne(rendezvousId);

			//añado al rendezvous el usuario logueado
			final List<User> users = r.getUsers();
			final User user = (User) this.loginService.getPrincipalActor();
			users.add(user);
			r.setUsers(users);
			this.rendezvousService.save(r);
			this.rendezvousService.flush();

			this.userService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();
	}
}
