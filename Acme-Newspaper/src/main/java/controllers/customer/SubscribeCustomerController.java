/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SubscribeService;
import controllers.AbstractController;
import domain.Subscribe;

@Controller
@RequestMapping("/subscribe/customer")
public class SubscribeCustomerController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscribeService	subscribeService;


	// Constructors -----------------------------------------------------------

	public SubscribeCustomerController() {
		super();
	}

	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(@RequestParam final int newspaperId) {
		final ModelAndView modelAndView;
		final Subscribe n = this.subscribeService.create(newspaperId);
		modelAndView = this.createEditModelAndView(n);
		return modelAndView;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Subscribe c, final BindingResult binding) {
		ModelAndView modelAndView;
		final String error = this.subscribeService.validate(c);
		if (binding.hasErrors() || error != null)
			modelAndView = this.createEditModelAndView(c, error);
		else
			try {
				this.subscribeService.save(c);
				modelAndView = new ModelAndView("redirect:/article/customer/list.do?newspaperId=" + c.getNewspaper().getId());
			} catch (final Exception e) {
				modelAndView = this.createEditModelAndView(c, "commit.error");
			}
		return modelAndView;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Subscribe subscribe) {
		ModelAndView result;

		result = this.createEditModelAndView(subscribe, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Subscribe subscribe, final String message) {
		ModelAndView result;

		result = new ModelAndView("subscribe/edit");
		result.addObject("subscribe", subscribe);
		result.addObject("message", message);

		return result;
	}

}
