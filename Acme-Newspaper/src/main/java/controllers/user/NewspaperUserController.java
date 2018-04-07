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

import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/user")
public class NewspaperUserController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public NewspaperUserController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/myList")
	public ModelAndView list() {
		ModelAndView result;

		final List<Newspaper> newspapers = (List<Newspaper>) this.userService.findByPrincipal().getNewspapers();
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("requestURI", "newspaper/list.do");

		return result;
	}

	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView modelAndView;

		final Newspaper n = this.newspaperService.create();

		modelAndView = this.createEditModelAndView(n);

		return modelAndView;

	}

	//Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEndorser(@RequestParam final int newspaperId) {
		ModelAndView result;
		Newspaper n;

		n = this.newspaperService.findOne(newspaperId);
		Assert.notNull(n);
		result = this.createEditModelAndView(n);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEndorser(@Valid final Newspaper n, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(n);
		else
			try {
				this.newspaperService.save(n);
				result = new ModelAndView("redirect:/newspaper/user/myList.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(n, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteEndorser(final Newspaper n, final BindingResult binding) {
		ModelAndView result;

		try {
			this.newspaperService.delete(n);
			result = new ModelAndView("redirect:/newspaper/user/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(n, "general.commit.error");
		}

		return result;
	}

	//Publish------------------------------------------------------------------------

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public ModelAndView publish(@RequestParam final int newspaperId) {
		ModelAndView result;
		final Newspaper n = this.newspaperService.findOne(newspaperId);

		if (this.newspaperService.publish(n))
			result = new ModelAndView("redirect:myList.do");
		else
			result = this.createEditModelAndView(n, "error.publish.articles");
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Newspaper newspaper) {
		ModelAndView result;

		result = this.createEditModelAndView(newspaper, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Newspaper newspaper, final String message) {
		ModelAndView result;

		result = new ModelAndView("newspaper/edit");
		result.addObject("newspaper", newspaper);
		result.addObject("message", message);

		return result;
	}

}
