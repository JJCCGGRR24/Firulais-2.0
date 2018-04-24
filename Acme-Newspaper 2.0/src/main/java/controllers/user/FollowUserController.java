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
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/follow/user")
public class FollowUserController extends AbstractController {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserService		userService;

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------

	public FollowUserController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<User> u = (List<User>) this.userService.getMyFollows();
		result = new ModelAndView("user/list");
		result.addObject("users", u);
		result.addObject("requestURI", "follow/user/list.do");
		result.addObject("follows", ((User) this.loginService.getPrincipalActor()).getFollows());
		return result;
	}

}
