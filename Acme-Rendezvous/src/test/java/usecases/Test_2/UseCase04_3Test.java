
package usecases.Test_2;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.RendezvousService;
import services.RequestService;
import services.ServicceService;
import utilities.AbstractTest;
import domain.CreditCard;
import domain.Request;
import domain.Servicce;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase04_3Test extends AbstractTest {

	//An actor who is authenticated as a user must be able to request a service for one of the rendezvouses that he or she’s created. 
	//He or she must specify a valid credit card in every request for a service.  Optionally, he or she can provide some comments in the request.

	// System under test ------------------------------------------------------
	@Autowired
	private RequestService		requestService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ServicceService		serviceService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {

			//Intentamos hacer una solicitud autenticados como User. El resultado del test debe ser positivo
			{
				"user1", 108, "Susana", "Visa", "4182543534348983", 2020, 12, 150, "Hola, ¿qué tal?", null
			},
			//Intentamos hacer una solicitud de un servicio autenticados como Manager. El resultado del test debe ser negativo 
			//ya que solos los Users pueden solicitar servicios.
			{
				"manager1", 108, "Susana", "Visa", "4182543534348983", 2020, 12, 150, "Hola, ¿qué tal?", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (int) testingData[i][7],
				(String) testingData[i][8],

				(Class<?>) testingData[i][9]);
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final int rendezvousId, final String holderName, final String brandName, final String number, final int expirationYear, final int expirationMonth, final int CVV, final String comment,
		final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			//Nos autenticamos
			this.authenticate(username);

			//Obtenemos el servicio que vamos a agregar a la solicitud
			final List<Servicce> s = (List<Servicce>) this.serviceService.findAll();
			final Servicce srvc = s.get(0);

			//Creamos la CreditCard que vamos a incluir en la solicitud
			final CreditCard cc = new CreditCard();

			cc.setHolderName(holderName);
			cc.setBrandName(brandName);
			cc.setNumber(number);
			cc.setExpirationYear(expirationYear);
			cc.setExpirationMonth(expirationMonth);
			cc.setCVV(CVV);

			//Creamos la solicitud
			final Request r = this.requestService.create(this.rendezvousService.findOne(rendezvousId));

			r.setComment(comment);
			r.setCreditCard(cc);
			r.setServicce(srvc);

			//Guardamos la solicitud
			this.requestService.save(r);

			//Nos deslogueamos
			this.unauthenticate();

			this.requestService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
