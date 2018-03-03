
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ServicceService;
import controllers.AbstractController;
import domain.Servicce;

@Controller
@RequestMapping("/servicce/administrator")
public class ServicceAdministratorController extends AbstractController {

	//Services-------------------------------------------------------------------------

	@Autowired
	private ServicceService	servicceService;


	//Constructor----------------------------------------------------------------------

	public ServicceAdministratorController() {
		super();
	}

	//Editing------------------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("service/list");
		final Collection<Servicce> servicces = this.servicceService.findAll();
		res.addObject("services", servicces);
		res.addObject("requestURI", "servicce/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/cancell", method = RequestMethod.GET)
	public ModelAndView cancell(final int servicceId) {
		ModelAndView modelAndView;

		final Servicce servicce = this.servicceService.findOne(servicceId);
		try {
			this.servicceService.cancell(servicce);
			modelAndView = new ModelAndView("redirect:/servicce/administrator/list.do");
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("The service is cancelled already"))
				modelAndView = this.createEditModelAndView(servicce, "servicce.cancelled.already");
			else
				modelAndView = this.createEditModelAndView(servicce, "servicce.commit.error");
		}
		return modelAndView;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Servicce servicce) {
		ModelAndView result;

		result = this.createEditModelAndView(servicce, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Servicce servicce, final String message) {
		ModelAndView result;

		result = new ModelAndView("service/list");
		final Collection<Servicce> servicces = this.servicceService.findAll();
		result.addObject("services", servicces);
		result.addObject("message", message);

		return result;
	}

}
