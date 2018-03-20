
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.User;
import services.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	//Services------------------------------------------------------------------------

	@Autowired
	private UserService userService;


	//Constructor---------------------------------------------------------------------

	public UserController() {
		super();
	}

	//Listing-------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView;

		final Collection<User> users = this.userService.findAll();

		modelAndView = new ModelAndView("user/list");
		modelAndView.addObject("users", users);
		modelAndView.addObject("requestURI", "user/list.do");

		return modelAndView;
	}

	//View-------------------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int userId) {
		ModelAndView modelAndView;

		final User user = this.userService.findOne(userId);

		modelAndView = new ModelAndView("user/view");
		modelAndView.addObject("user", user);
		modelAndView.addObject("requestURI", "user/view.do?userId=" + userId);

		return modelAndView;
	}

}
