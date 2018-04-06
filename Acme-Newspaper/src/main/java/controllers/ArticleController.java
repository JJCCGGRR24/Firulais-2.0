
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.UserService;
import domain.Article;
import domain.User;

@Controller()
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services
	@Autowired
	private ArticleService	articleService;

	@Autowired
	private UserService		userService;


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

}
