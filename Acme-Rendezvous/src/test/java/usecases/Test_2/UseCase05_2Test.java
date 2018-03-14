
package usecases.Test_2;

import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Manager;
import domain.Servicce;
import services.ManagerService;
import services.ServicceService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase05_2Test extends AbstractTest {

	//	5. An actor who is registered as a manager must be able to:
	//		2.Manage his or her services, which includes:
	//			listing them,
	//			creating them,
	//			updating them,
	//			and deleting them as long as they are not required by any rendezvouses.

	// System under test ------------------------------------------------------
	@Autowired
	private ServicceService	servicceService;

	@Autowired
	private ManagerService	managerService;

	// Tests ------------------------------------------------------------------


	//Listing Test-------------------------------------------------------------

	@Test
	public void driverList() {
		System.out.println("*************** LIST TEST ******************");
		final Object testingData[][] = {
			{
				"manager1", null
			}, {
				"user1", NullPointerException.class // Nos Autenticamos como un User
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateList((String) testingData[i][0], (Class<?>) testingData[i][1]);

		System.out.println("*********************************************");
	}

	@Test
	public void driverCreate() {
		System.out.println("*************** CREATE TEST ******************");
		final Object testingData[][] = {
			{
				"manager1", "prueba1", "Description prueba 1", "http://prueba1.com", null
			}, {
				"user1", "prueba1", "Description prueba 1", "http://prueba1.com", ClassCastException.class // Nos Autenticamos como un User
			}, {
				"manager1", "", "Description prueba 1", "http://prueba1.com", ConstraintViolationException.class // Un dato esta blanco
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);

		System.out.println("*********************************************");
	}

	@Test
	public void driverUpdate() {
		System.out.println("*************** UPDATE TEST ******************");
		final Object testingData[][] = {
			{
				"manager1", "servicce1", "Description prueba 1", null
			}, {
				"user1", "servicce1", "Description prueba 1", IllegalArgumentException.class // Nos Autenticamos como un User
			}, {
				"manager2", "servicce1", "Description prueba 1", IllegalArgumentException.class // Actualizar un servicce que no le pertenece
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateUpdate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

		System.out.println("*********************************************");
	}

	@Test
	public void driverDelete() {
		System.out.println("*************** DELETE TEST ******************");
		final Object testingData[][] = {
			{
				"manager1", "servicce1", null
			}, {
				"user1", "servicce1", NullPointerException.class // Nos Autenticamos como un User
			}, {
				"manager2", "servicce1", NullPointerException.class // Actualizar un servicce que no le pertenece
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

		System.out.println("*********************************************");
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateList(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos como MANAGER
			this.authenticate(username);
			final int managerId = this.getEntityId(username);
			final Manager manager = this.managerService.findOne(managerId);

			//Muestro todos los Servicces del sistema
			final ArrayList<Servicce> list = new ArrayList<>(manager.getServicces());
			for (final Servicce servicce : list)
				System.out.println(servicce.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);

			System.out.println("-------------------------");
		}

		this.checkExceptions(expected, caught);

	}

	protected void templateCreate(final String username, final String name, final String description, final String picture, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos como MANAGER
			this.authenticate(username);

			//Creamos un Servicce
			final Servicce servicce = this.servicceService.create();
			servicce.setName(name);
			servicce.setDescription(description);
			servicce.setPicture(picture);

			final Servicce saved = this.servicceService.save(servicce);
			Assert.isTrue(this.servicceService.findAll().contains(saved));

			//Muestro todos los Servicces del sistema

			System.out.println(servicce.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-------------------------");
		}

		this.checkExceptions(expected, caught);

	}

	protected void templateUpdate(final String username, final String name, final String description, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos como MANAGER
			this.authenticate(username);

			//Buscamos un Servicce
			final int serviceId = this.getEntityId(name);
			final Servicce servicce = this.servicceService.findOne(serviceId);
			servicce.setDescription(description);

			final Servicce saved = this.servicceService.save(servicce);
			Assert.isTrue(this.servicceService.findAll().contains(saved));

			//Muestro todos los Servicces del sistema

			System.out.println(servicce.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-------------------------");
		}

		this.checkExceptions(expected, caught);

	}

	protected void templateDelete(final String username, final String name, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			//Nos autenticamos como MANAGER
			this.authenticate(username);

			//Buscamos un Servicce
			final int serviceId = this.getEntityId(name);
			final Servicce servicce = this.servicceService.findOne(serviceId);

			this.servicceService.delete(servicce);
			Assert.isTrue(!this.servicceService.findAll().contains(servicce));

			//Muestro todos los Servicces del sistema

			System.out.println(servicce.getName());

			//Nos desautenticamos
			this.unauthenticate();

			System.out.println("Mostrados correctamente.");
			System.out.println("-------------------------");
		} catch (final Throwable oops) {
			caught = oops.getClass();
			System.out.println(caught);
			System.out.println("-------------------------");
		}

		this.checkExceptions(expected, caught);

	}

}
