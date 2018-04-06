
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import controllers.AbstractController;
import domain.Article;

@Controller
@RequestMapping("/newspaper/admin")
public class ArticleAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private ArticleService	articleService;


	//Constructor--------------------------------------------------------------------

	public ArticleAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int articleId) {
		ModelAndView modelAndView;

		final Article article = this.articleService.findOne(articleId);

		try {
			this.articleService.delete(article);
			modelAndView = new ModelAndView("redirect:/article/list.do");
		} catch (final Throwable throwable) {
			modelAndView = new ModelAndView("redirect:/article/list.do");
			modelAndView.addObject("message", "newspaper.commit.error");
		}

		return modelAndView;
	}
}
