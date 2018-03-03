
package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Rendezvous;
import domain.User;
import security.LoginService;
import services.RendezvousService;
import services.UserService;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	//Services-------------------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;

	@Autowired
	private LoginService		loginService;

	//---------------------------------------------------------------------------------


	//Constructor----------------------------------------------------------------------

	public RendezvousController() {
		super();
	}

	//Listing--------------------------------------------------------------------------

	@RequestMapping(value = "/all/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int userId) {
		final ModelAndView modelAndView;
		modelAndView = new ModelAndView("rendezvous/list");

		final List<Rendezvous> rendezvous1 = this.rendezvousService.listRSVPdPast(userId);
		final List<Rendezvous> rendezvous2 = this.rendezvousService.listRSVPdFuture(userId);

		modelAndView.addObject("rendezvous1", this.userService.removeAdultsOnly(rendezvous1));
		modelAndView.addObject("rendezvous2", this.userService.removeAdultsOnly(rendezvous2));

		modelAndView.addObject("requestURI", "rendezvous/all/list.do?userId=" + userId);
		return modelAndView;
	}

	@RequestMapping("/all/links")
	public ModelAndView links(@RequestParam final int rendezvousId) {
		final ModelAndView res = new ModelAndView("rendezvous/links");
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		res.addObject("links", r.getLinkedTo());
		res.addObject("rendezvous", r);
		res.addObject("requestURI", "question/all/links.do");
		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView;
		modelAndView = new ModelAndView("rendezvous/list");

		final Collection<Rendezvous> rendezvous1 = this.rendezvousService.listRendezvousPast();
		final Collection<Rendezvous> rendezvous2 = this.rendezvousService.listRendezvousFuture();

		if (this.userService.isAuthenticated() == true) {

			if (this.userService.isUser() == true) {
				final User principal = this.userService.findByPrincipal();
				modelAndView.addObject("principal", principal);
				modelAndView.addObject("myRs", ((User) this.loginService.getPrincipalActor()).getRSVPd());
				final List<Rendezvous> rsvp = ((User) this.loginService.getPrincipalActor()).getRSVPd();
				modelAndView.addObject("rsvp", rsvp);
				if (this.userService.isAdult(principal)) {
					modelAndView.addObject("rendezvous1", rendezvous1);
					modelAndView.addObject("rendezvous2", rendezvous2);
				} else {
					modelAndView.addObject("rendezvous1", this.rendezvousService.isNotAthenticated(rendezvous1));
					modelAndView.addObject("rendezvous2", this.rendezvousService.isNotAthenticated(rendezvous2));
				}
			} else {
				modelAndView.addObject("rendezvous1", rendezvous1);
				modelAndView.addObject("rendezvous2", rendezvous2);
			}
		} else {
			modelAndView.addObject("rendezvous1", this.rendezvousService.isNotAthenticated(rendezvous1));
			modelAndView.addObject("rendezvous2", this.rendezvousService.isNotAthenticated(rendezvous2));
		}

		modelAndView.addObject("requestURI", "rendezvous/list.do");
		return modelAndView;
	}
}
