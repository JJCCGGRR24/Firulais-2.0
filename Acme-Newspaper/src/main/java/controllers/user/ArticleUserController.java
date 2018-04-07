/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;

@Controller
@RequestMapping("/article/user")
public class ArticleUserController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ArticleService	articleService;

	@Autowired
	private UserService		userService;


	// Constructors -----------------------------------------------------------

	public ArticleUserController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/myList")
	public ModelAndView myList() {
		ModelAndView result;
		final List<Article> articles = (List<Article>) this.userService.findByPrincipal().getArticles();
		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/user/myList.do");
		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<Article> articles = (List<Article>) this.articleService.findAll();
		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/user/list.do");
		return result;
	}

	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView modelAndView;
		final Article n = this.articleService.create();
		modelAndView = this.createEditModelAndView(n);
		return modelAndView;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Article article) {
		ModelAndView result;
		result = this.createEditModelAndView(article, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Article article, final String message) {
		ModelAndView result;
		result = new ModelAndView("article/edit");
		result.addObject("article", article);
		result.addObject("message", message);
		return result;
	}

}
