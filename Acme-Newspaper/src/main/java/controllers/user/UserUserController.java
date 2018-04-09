
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.User;
import security.LoginService;
import services.UserService;

@Controller
@RequestMapping("/user/user")
public class UserUserController extends AbstractController {

	//services
	@Autowired
	private UserService		userService;

	@Autowired
	private LoginService	loginService;


	//Construct
	public UserUserController() {
		super();
	}

	//List
	@RequestMapping("/list")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("user/list");
		final Collection<User> users = this.userService.findAll();
		res.addObject("users", users);
		res.addObject("requestURI", "user/list.do");
		res.addObject("follows", ((User) this.loginService.getPrincipalActor()).getFollows());
		return res;

	}

	//Follow
	@RequestMapping("/follow")
	public ModelAndView follow(@RequestParam final int userId) {

		this.userService.follow(userId);
		return new ModelAndView("redirect:/user/user/list.do");

	}

	//Unfollow
	@RequestMapping("/unfollow")
	public ModelAndView unfollow(@RequestParam final int userId) {

		this.userService.unfollow(userId);
		return new ModelAndView("redirect:/user/user/list.do");

	}

}
