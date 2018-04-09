/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.NewspaperService;
import services.SubscribeService;
import domain.Customer;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	//Services------------------------------------------------------------------------

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private SubscribeService	subscribeService;


	// Constructors -----------------------------------------------------------

	public NewspaperController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		final List<Newspaper> newspapers = this.newspaperService.getPublishedNewspapers();
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);

		result.addObject("requestURI", "newspaper/list.do");

		return result;
	}

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam final int newspaperId) {
		ModelAndView result;
		result = new ModelAndView("newspaper/details");
		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);
		try {
			//para comprobar que esta logueado.
			//getPrincipal() devuelve IlegalArgumentException si no estas logueado
			if (LoginService.getPrincipal() != null)
				try {
					final Customer c = (Customer) this.loginService.getPrincipalActor();
					if (this.subscribeService.estaSubscrito(c, newspaper))
						result.addObject("customerEstaSubscrito", true);

				} catch (final Exception e) {
				}
		} catch (final IllegalArgumentException e) {
			// TODO: handle exception
		}

		result.addObject("newspaper", newspaper);
		return result;
	}

}
