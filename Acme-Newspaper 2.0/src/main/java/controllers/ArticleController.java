
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Article;
import domain.Customer;
import domain.Newspaper;
import domain.User;
import security.LoginService;
import services.AdvertisementService;
import services.ArticleService;
import services.SubscribeService;
import services.UserService;

@Controller()
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services
	@Autowired
	private ArticleService			articleService;

	@Autowired
	private UserService				userService;
	@Autowired
	private SubscribeService		subscribeService;
	@Autowired
	private LoginService			loginService;
	@Autowired
	private AdvertisementService	advertisementService;


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
		result.addObject("requestURI", "article/publicList.do");
		return result;
	}

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam final int articleId) {
		ModelAndView result;
		final Article article = this.articleService.findOne(articleId);
		final String banner = this.advertisementService.chooseOne().getBanner();

		if (this.subscribeService.tienePermisoParaVerArticulos(article)) {
			//muestra el articulo
			result = new ModelAndView("article/details");
			result.addObject("article", article);
			result.addObject("banner", banner);
		} else {
			//muestra el details del periodico diciendo que no puede ver los articulos
			result = new ModelAndView("newspaper/details");
			final Newspaper newspaper = article.getNewspaper();
			try {
				final Customer c = (Customer) this.loginService.getPrincipalActor();
				if (this.subscribeService.estaSubscrito(c, newspaper)) {
					result.addObject("customerEstaSubscrito", true);
					result.addObject("banner", banner);
				}

			} catch (final Exception e) {
			}
			result.addObject("newspaper", newspaper);
			result.addObject("articles", newspaper.getArticles());
			result.addObject("banner", banner);
		}

		return result;
	}

}
