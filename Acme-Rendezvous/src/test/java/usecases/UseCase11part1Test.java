
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
	public void driver() {
		final Object testingData[][] = {
			{
				//accedemos a crear categoria con admin, no debe de haber problema pues es el unico que puede
				//crear categorias
				"admin", "Difficult", "Very very difficult", "Qeusqoo", "servicce1", null
			}, {
				//accedemos a crear categoria con user, debería de saltar la excepción pues esto es ilegal
				"user1", "Difficult", "Very very difficult", "Qeusqoo", "servicce1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], super.getEntityId((String) testingData[i][4]), super.getEntityId((String) testingData[i][4]), (Class<?>) testingData[i][5]);

	}

	protected void template(final String username, final String name, final String description, final int categoryId, final int servicceId, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			//me autentico 
			super.authenticate(username);

			//creo categoria
			final Category cat = this.categoryService.create();

			//introduzco datos a la categoria
			cat.setName(name);
			cat.setDescription(description);
			final Category catFather = this.categoryService.findOne(categoryId);
			cat.setCategoryFather(catFather);
			final List<Servicce> servicces = new ArrayList<Servicce>();
			servicces.add(this.servicceService.findOne(servicceId));
			cat.setServicces(servicces);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
//	@Test
//	public void testCreateCategoryByAdmin() {
//
//		super.authenticate("user1");
//		final Category cat = this.categoryService.create();
//
//		cat.setName("Difficult");
//
//		cat.setDescription("Very very difficult");
//
//		final int categoryId = super.getEntityId("Qeusqoo");
//		cat.setCategoryFather(this.categoryService.findOne(categoryId));
//
//		final int servicceId = super.getEntityId("servicce1");
//		final List<Servicce> servicces = new ArrayList<Servicce>();
//		servicces.add(this.servicceService.findOne(servicceId));
//		cat.setServicces(servicces);
//
//		super.unauthenticate();
//	}

