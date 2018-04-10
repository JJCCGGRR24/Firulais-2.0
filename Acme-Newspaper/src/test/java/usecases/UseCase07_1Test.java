
package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ArticleService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase07_1Test extends AbstractTest {

	//SUT

	//	@Autowired
	//	private SubscribeService	subscribeService;

	@Autowired
	private ArticleService	articleService;


	//	7. An actor who is authenticated as an administrator must be able to:
	//		1. Remove an article that he or she thinks is inappropriate.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//eliminamos como admin un articulo no publicado
				"article1", null
			}, {
				//eliminamos como user un articulo de otra persona
				"article7", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate("user1");
			this.articleService.delete(this.articleService.findOne(this.getEntityId(username)));
			super.authenticate(null);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
