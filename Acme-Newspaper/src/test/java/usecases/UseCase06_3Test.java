
package usecases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ArticleService;
import services.NewspaperService;
import utilities.AbstractTest;
import domain.Article;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UseCase06_3Test extends AbstractTest {

	//SUT

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ArticleService		articleService;


	// 6. An actor who is authenticated as a user must be able to:
	// 3. Write an article and attach it to any newspaper that has not been published, yet.
	// Note that articles may be saved in draft mode, which allows to modify them later, or
	// final model, which freezes them forever.

	@Test
	public void driver() {
		final Object testingData[][] = {
			{

				//comprobaremos que un user puede editar un articlecorrectamente
				//ya que cumple todo los requisitos
				"user1", "title1", "24/10/2018 00:00", "summary1", "body1", "https://www.youtube.com/watch?v=GwrnjtW-rfk", "article1", null
			}, {
				//comprobaremos que un user al intentar editar un article no se le permitirá
				//porque está ya en modo final
				"user3", "title1", "24/10/2018 00:00", "summary1", "body1", "https://www.youtube.com/watch?v=GwrnjtW-rfk", "article2", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], super.getEntityId((String) testingData[i][6]),
				((Class<?>) testingData[i][7]));

	}
	protected void template(final String username, final String title, final String moment, final String summary, final String body, final String pictures, final int articleId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(username);

			//buscamos el articulo
			final Article article = this.articleService.findOne(articleId);

			//editamos el articulo
			article.setTitle(title);
			final SimpleDateFormat pattern = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			final Date date = pattern.parse(moment);

			article.setMoment(date);
			article.setSummary(summary);
			article.setBody(body);

			final Collection<String> pics = new ArrayList<String>();
			pics.add(pictures);
			article.setPictures(pics);

			this.articleService.save(article);
			this.articleService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		super.unauthenticate();

	}
}
