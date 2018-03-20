
package controllers.rendezvous;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;
import services.RendezvousService;

@Controller
@RequestMapping("/user/rendezvous")
public class UserRendezvousController extends AbstractController {

	//Service-------------------------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;

	//Constructor---------------------------------------------------------------------


	public UserRendezvousController() {
		super();
	}

	//Listing-------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId) {
		ModelAndView modelAndView;

		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		final Collection<User> users = rendezvous.getUsers();

		modelAndView = new ModelAndView("user/list");
		modelAndView.addObject("users", users);
		modelAndView.addObject("requestURI", "user/rendezvous/list.do?rendezvous=" + rendezvousId);

		return modelAndView;
	}
}
