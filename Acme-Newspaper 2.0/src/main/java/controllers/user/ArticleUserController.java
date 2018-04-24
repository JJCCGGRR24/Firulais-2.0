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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ArticleService;
import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;

@Controller
@RequestMapping("/article/user")
public class ArticleUserController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ArticleService		articleService;

	@Autowired
	private UserService			userService;

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private LoginService		loginService;


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

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int articleId) {
		final ModelAndView modelAndView;
		final Article n = this.articleService.findOne(articleId);
		Assert.isTrue(n.getUser().getId() == this.loginService.getPrincipalActor().getId());
		Assert.isTrue(n.getNewspaper().getPublicationDate() == null);
		Assert.isTrue(n.getMoment() == null);
		Assert.isTrue(n.isFinalMode() == false);
		modelAndView = this.createEditModelAndView(n);
		return modelAndView;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Article c, final BindingResult binding) {
		return this.savePublishModelAndView(c, binding, false);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "publish")
	public ModelAndView publish(@Valid final Article c, final BindingResult binding) {
		return this.savePublishModelAndView(c, binding, true);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Article c, final BindingResult binding) {
		this.articleService.delete(c);
		return new ModelAndView("redirect:/article/user/myList.do");
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView savePublishModelAndView(final Article c, final BindingResult binding, final boolean publish) {
		ModelAndView modelAndView;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(c, null);
		else {
			final String error = this.articleService.validate(c);
			if (error != null)
				modelAndView = this.createEditModelAndView(c, error);
			else
				try {
					c.setFinalMode(publish);
					this.articleService.save(c);
					modelAndView = new ModelAndView("redirect:/article/user/myList.do");
				} catch (final Exception e) {
					modelAndView = this.createEditModelAndView(c, "commit.error");
					System.out.println(e.getStackTrace());
					e.printStackTrace();
					System.out.println(e.toString());
					System.out.println(e.getLocalizedMessage());
					System.out.println(e.getMessage());
					System.out.println(e.getCause());
				}
		}
		return modelAndView;
	}

	protected ModelAndView createEditModelAndView(final Article article) {
		ModelAndView result;
		result = this.createEditModelAndView(article, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Article article, final String message) {
		ModelAndView result;
		result = new ModelAndView("article/edit");
		result.addObject("newspapers", this.newspaperService.getNotPublishedNewspapers());
		result.addObject("article", article);
		result.addObject("message", message);
		return result;
	}

}
