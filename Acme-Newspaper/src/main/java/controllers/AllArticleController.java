
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import domain.Article;

@Controller
@RequestMapping("/article/all")
public class AllArticleController extends AbstractController {

	//Services ---------------------------------------------------------------
	@Autowired
	private ArticleService	articleService;


	// Constructors -----------------------------------------------------------
	public AllArticleController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String search) {
		final ModelAndView res = new ModelAndView("article/list");
		List<Article> l = null;
		if (search != null)
			l = (List<Article>) this.articleService.search(search);

		res.addObject("articles", l);

		res.addObject("requestURI", "article/all/list.do");

		return res;
	}

}
