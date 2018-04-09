
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import controllers.AbstractController;
import domain.Chirp;

@Controller
@RequestMapping("/chirp/admin")
public class ChirpAdministratorController extends AbstractController {

	//Service--------------------------------------------------------

	@Autowired
	private ChirpService	chirpService;


	//Constructor----------------------------------------------------

	public ChirpAdministratorController() {
		super();
	}

	//Listing--------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView;

		final Collection<Chirp> chirps = this.chirpService.findAll();

		modelAndView = new ModelAndView("chirp/list");
		modelAndView.addObject("chirps", chirps);
		modelAndView.addObject("requestURI", "chirp/admin/list.do");

		return modelAndView;
	}

	//Deleting-------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int chirpId) {
		ModelAndView modelAndView;

		try {
			final Chirp chirp = this.chirpService.findOne(chirpId);
			this.chirpService.delete(chirp);
			modelAndView = new ModelAndView("redirect:/chirp/admin/list.do");
		} catch (final Throwable throwable) {
			modelAndView = new ModelAndView("redirect:/chirp/admin/list.do");
			modelAndView.addObject("message", "chirp.commit.error");
		}

		return modelAndView;
	}
}
