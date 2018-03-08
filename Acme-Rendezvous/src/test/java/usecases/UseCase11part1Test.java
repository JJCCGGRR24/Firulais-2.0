
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


	//	An actor who is authenticated as an administrator must be able to:
	//	Manage the categories of services, which includes listing, creating, updating, 
	//	deleting, and re-organising them in the category hierarchies.

	@Test
	public void testCreateCategoryByAdmin() {

		super.authenticate("user1");
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
