
package usecases;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ArticleService;
import utilities.AbstractTest;
import domain.Article;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase17_2Test extends AbstractTest {

	//SUT

	//	@Autowired
	//	private TabooService		tabooService;
	//
	//	@Autowired
	//	private NewspaperService	newspaperService;

	@Autowired
	private ArticleService	articleService;


	//	17. An actor who is authenticated as an administrator must be able to:
	//		2.List the articles that contain taboo words.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//Accedo a la lista y el tamaño de la lista debe ser 2. Todo correcto.
				"admin", 1, null
			}, {
				//Accedo a la lista pensando que el tamaño es 0 cuando debe ser 2. IlegalArgumentException
				"admin", 0, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void template(final String username, final int numArticlesTaboo, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);
			//listar
			final List<Article> articles = this.articleService.getArticlesTabooWords();
			System.out.println(articles.size());
			Assert.isTrue(articles.size() == numArticlesTaboo);
			super.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
