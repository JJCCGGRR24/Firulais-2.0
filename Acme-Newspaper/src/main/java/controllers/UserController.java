
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/details")
	public ModelAndView details(@RequestParam final int userId) {
		final ModelAndView res = new ModelAndView("user/details");
		final User user = this.userService.findOne(userId);
		res.addObject("user", user);
		res.addObject("requestURI", "user/details.do");
		return res;

	}

}
