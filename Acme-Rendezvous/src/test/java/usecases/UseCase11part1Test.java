
package usecases;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CategoryService;
import services.ServicceService;
import utilities.AbstractTest;
import domain.Category;
import domain.Servicce;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase11part1Test extends AbstractTest {

	//SUT
	@Autowired
	private CategoryService	categoryService;

	@Autowired
	private ServicceService	servicceService;


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
	public void testCreateCategoryByAdmin() {

		super.authenticate("use1");
		final Category cat = this.categoryService.create();

		cat.setName("Difficult");

		cat.setDescription("Very very difficult");

		final int categoryId = super.getEntityId("Qeusqoo");
		cat.setCategoryFather(this.categoryService.findOne(categoryId));

		final int servicceId = super.getEntityId("servicce1");
		final List<Servicce> servicces = new ArrayList<Servicce>();
		servicces.add(this.servicceService.findOne(servicceId));
		cat.setServicces(servicces);

		super.unauthenticate();
	}
}
