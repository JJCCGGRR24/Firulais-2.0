
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ArticleService;
import services.SubscribeService;
import services.UserService;
import domain.Article;
import domain.Customer;
import domain.Newspaper;
import domain.User;

@Controller()
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services
	@Autowired
	private ArticleService		articleService;

	@Autowired
	private UserService			userService;
	@Autowired
	private SubscribeService	subscribeService;
	@Autowired
	private LoginService		loginService;


	//Constructor
	public ArticleController() {
		super();
	}

	//List

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int userId) {
		final ModelAndView res = new ModelAndView("article/list");
		final User user = this.userService.findOne(userId);
		final List<Article> articles = this.articleService.findByUser(user);
		res.addObject("articles", articles);
		res.addObject("requestURI", "article/list.do");
		return res;

	}

	@RequestMapping("/publicList")
	public ModelAndView publicList() {
		ModelAndView result;
		final List<Article> articles = this.articleService.publicArticles();
		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/user/publicList.do");
		return result;
	}

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam final int articleId) {
		ModelAndView result;
		final Article article = this.articleService.findOne(articleId);

		if (this.subscribeService.tienePermisoParaVerArticulos(article)) {
			//muestra el articulo
			result = new ModelAndView("article/details");
			result.addObject("article", article);
		} else {
			//muestra el details del periodico diciendo que no puede ver los articulos
			result = new ModelAndView("newspaper/details");
			final Newspaper newspaper = article.getNewspaper();
			try {
				final Customer c = (Customer) this.loginService.getPrincipalActor();
				if (this.subscribeService.estaSubscrito(c, newspaper))
					result.addObject("customerEstaSubscrito", true);

			} catch (final Exception e) {
			}
			result.addObject("newspaper", newspaper);
			result.addObject("articles", newspaper.getArticles());
		}

		return result;
	}

}
