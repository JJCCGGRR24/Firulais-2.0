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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ArticleService;
import services.FollowUpService;
import controllers.AbstractController;
import domain.FollowUp;

@Controller
@RequestMapping("/followUp/user")
public class FollowUpUserController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private FollowUpService	followUpService;

	//	@Autowired
	//	private UserService		userService;

	@Autowired
	private ArticleService	articleService;

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------

	public FollowUpUserController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/myList")
	public ModelAndView myList() {
		ModelAndView result;
		final List<FollowUp> followUps = this.followUpService.getFollowUpsByUser(this.loginService.getPrincipalActor().getId());
		result = new ModelAndView("followUp/list");
		result.addObject("followUps", followUps);
		result.addObject("requestURI", "followUp/user/myList.do");
		return result;
	}

	//	@RequestMapping("/list")
	//	public ModelAndView list() {
	//		ModelAndView result;
	//		final List<FollowUp> followUps = (List<FollowUp>) this.followUpService.findAll();
	//		result = new ModelAndView("followUp/list");
	//		result.addObject("followUps", followUps);
	//		result.addObject("requestURI", "followUp/user/list.do");
	//		return result;
	//	}

	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView modelAndView;
		final FollowUp n = this.followUpService.create();
		modelAndView = this.createEditModelAndView(n);
		return modelAndView;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FollowUp c, final BindingResult binding) {
		return this.savePublishModelAndView(c, binding);
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView savePublishModelAndView(final FollowUp c, final BindingResult binding) {
		ModelAndView modelAndView;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(c, null);
		else {
			final String error = this.followUpService.validate(c);
			if (error != null)
				modelAndView = this.createEditModelAndView(c, error);
			else
				try {
					this.followUpService.save(c);
					modelAndView = new ModelAndView("redirect:/followUp/user/myList.do");
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

	protected ModelAndView createEditModelAndView(final FollowUp followUp) {
		ModelAndView result;
		result = this.createEditModelAndView(followUp, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final FollowUp followUp, final String message) {
		ModelAndView result;
		result = new ModelAndView("followUp/edit");
		result.addObject("articles", this.articleService.getPublishedArticlesByUser(this.loginService.getPrincipalActor().getId()));
		result.addObject("followUp", followUp);
		result.addObject("message", message);
		return result;
	}

}
