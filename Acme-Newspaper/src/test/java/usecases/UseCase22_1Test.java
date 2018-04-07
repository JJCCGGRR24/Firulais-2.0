
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.NewspaperService;
import services.SubscribeService;
import utilities.AbstractTest;
import domain.CreditCard;
import domain.Subscribe;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase22_1Test extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private SubscribeService	subscribeService;


	//	22. An actor who is authenticated as a customer can:
	//		1. Subscribe to a private newspaper by providing a valid credit card

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//comprobaremos que un customer puede suscribirse a un newspaper si aporta una
				//tarjeta de credito valida
				"customer1", "Jose Carlos", "VISA", "4000990618528905", 2020, 02, 450, "newspaper1_3", null
			}, {
				//comprobaremos que un customer no puede suscribirse a un newspapaer si aporta una 
				//tarjeta de credito inválida
				"customer1", "Jose Carlos", "VISA", "4000990618528905", 1996, 02, 450, "newspaper1_3", null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (int) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6],
				super.getEntityId((String) testingData[i][7]), (Class<?>) testingData[i][8]);

	}
	protected void template(final String username, final String holderName, final String brandName, final String number, final int expirationYear, final int expirationMonth, final int cvv, final int newspaperId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			final CreditCard creditCard = new CreditCard();
			creditCard.setBrandName(brandName);
			creditCard.setCVV(cvv);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setHolderName(holderName);
			creditCard.setNumber(number);

			final Subscribe suscribe = this.subscribeService.create(newspaperId);
			suscribe.setCreditCard(creditCard);

			//			final Newspaper news = this.newspaperService.findOne(newspaperId);
			//			suscribe.setNewspaper(news);

			this.subscribeService.save(suscribe);

			this.subscribeService.flush();
			this.newspaperService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
