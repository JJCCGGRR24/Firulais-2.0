
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Article;
import services.ArticleService;

@Controller
@RequestMapping("/article/admin")
public class ArticleAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private ArticleService articleService;


	//Constructor--------------------------------------------------------------------

	public ArticleAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView;

		final Collection<Article> articles = this.articleService.getPublishedArticles();

		modelAndView = new ModelAndView("article/list");
		modelAndView.addObject("articles", articles);
		modelAndView.addObject("requestURI", "article/admin/list.do");

		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int articleId) {
		ModelAndView modelAndView;

		final Article article = this.articleService.findOne(articleId);

		try {
			this.articleService.delete(article);
			modelAndView = new ModelAndView("redirect:/article/admin/list.do");
		} catch (final Throwable throwable) {
			modelAndView = new ModelAndView("redirect:/article/admin/list.do");
			modelAndView.addObject("message", "newspaper.commit.error");
		}

		return modelAndView;
	}
}
