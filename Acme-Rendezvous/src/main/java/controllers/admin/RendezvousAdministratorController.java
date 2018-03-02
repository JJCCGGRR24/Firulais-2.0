
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Rendezvous;
import services.RendezvousService;

@Controller
@RequestMapping("/rendezvous/admin")
public class RendezvousAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;


	//Constructor--------------------------------------------------------------------

	public RendezvousAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int rendezvousId) {
		ModelAndView modelAndView;

		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);

		try {
			this.rendezvousService.delete(rendezvous);
			modelAndView = new ModelAndView("redirect:/rendezvous/list.do");
		} catch (final Throwable throwable) {
			modelAndView = new ModelAndView("redirect:/rendezvous/list.do");
			modelAndView.addObject("message", "rendezvous.commit.error");
		}

		return modelAndView;
	}

}
