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
import services.ChirpService;
import controllers.AbstractController;
import domain.Chirp;

@Controller
@RequestMapping("/chirp/user")
public class ChirpUserController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------

	public ChirpUserController() {
		super();
	}

	//Creating------------------------------------------------------------------------

	@RequestMapping("/followeds")
	public ModelAndView followeds() {
		final ModelAndView res = new ModelAndView("chirp/list");
		final List<Chirp> chirps = this.chirpService.getChirpsFromFolloweds();
		res.addObject("chirps", chirps);
		res.addObject("requestURI", "chirp/user/followeds.do");
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView modelAndView;
		final Chirp n = this.chirpService.create();
		modelAndView = this.createModelAndView(n);
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chirp c, final BindingResult binding) {
		ModelAndView modelAndView;
		if (binding.hasErrors())
			modelAndView = this.createModelAndView(c);
		else
			try {
				this.chirpService.save(c);
				modelAndView = new ModelAndView("redirect:/user/details.do?userId=" + c.getUser().getId());
			} catch (final Exception e) {
				modelAndView = this.createModelAndView(c, "commit.error");
			}
		return modelAndView;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createModelAndView(final Chirp chirp) {
		ModelAndView result;
		result = this.createModelAndView(chirp, null);
		return result;
	}

	protected ModelAndView createModelAndView(final Chirp chirp, final String message) {
		ModelAndView result;
		result = new ModelAndView("chirp/edit");
		result.addObject("chirp", chirp);
		result.addObject("requestURI", "chirp/user/edit.do");
		result.addObject("message", message);
		return result;
	}

}
