
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ManagerService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase06part2Test extends AbstractTest {

	//SUT
	@Autowired
	private ManagerService	managerService;


	//An actor who is authenticated as an administrator must be able to display a dashboard 
	//with the following information:
	//		The best-selling services.
	//		The managers who provide more services than the average.
	//		The managers who have got more services cancelled.
	//		The average number of categories per rendezvous.
	//		The average ratio of services in each category.
	//	 	The average, the minimum, the maximum, and the standard deviation of services requested per rendezvous.
	//	 	The top-selling services.

	@Test
	public void testDisplayDashboardByAdmin() {

		super.authenticate("admin");
		this.managerService.queryNewC2();
		super.unauthenticate();
	}

	@Test
	public void testDisplayDashboardByOtherActor() {

		super.authenticate("user1");
		this.managerService.queryNewC2();
		super.unauthenticate();
	}

}
