
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	//services
	@Autowired
	private UserService	userService;


	//Construct
	public UserController() {
		super();
	}

	//List
	@RequestMapping("/list")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("user/list");
		final Collection<User> users = this.userService.findAll();
		res.addObject("users", users);
		res.addObject("requestURI", "user/list.do");
		return res;

	}

}
