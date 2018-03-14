
package usecases.Test_2;

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
	public void testDisplayDashboardByAdmin() {

		super.authenticate("admin");

		//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
		//como el autenticado es admin se le permite el acceso a dichas queries
		this.servicceService.queryNewC1B4();
		this.managerService.queryNewC2();
		this.managerService.queryNewC3();
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDisplayDashboardByUser() {

		//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
		//como el autenticado es user1 no se le permite el acceso a dichas queries
		super.authenticate("user1");
		this.servicceService.queryNewC1B4();
		this.managerService.queryNewC2();
		this.managerService.queryNewC3();
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDisplayDashboardByManager() {

		//Se intenta acceder a las queries del dashboard pertenecientes al caso de uso 6.2
		//como el autenticado es manager1 no se le permite el acceso a dichas queries
		super.authenticate("manager1");
		this.servicceService.queryNewC1B4();
		this.managerService.queryNewC2();
		this.managerService.queryNewC3();
		super.unauthenticate();
	}

}
