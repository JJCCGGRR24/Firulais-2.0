
package usecases.tests_v2;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ManagerService;
import services.ServicceService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase06_2Test extends AbstractTest {

	//SUT
	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ServicceService	servicceService;


	//An actor who is authenticated as an administrator must be able to display a dashboard 
	//with the following information:
	//		The best-selling services.
	//		The managers who provide more services than the average.
	//		The managers who have got more services cancelled.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
				//como el autenticado es admin se le permite el acceso a dichas queries
				"admin", null

			}, {
				//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
				//como el autenticado es user1 no se le permite el acceso a dichas queries
				"user1", IllegalArgumentException.class

			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	protected void template(final String username, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
			//como el autenticado es manager1 no se le permite el acceso a dichas queries
			super.authenticate(username);
			this.servicceService.queryNewC1B4();
			this.managerService.queryNewC2();
			this.managerService.queryNewC3();
			super.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
