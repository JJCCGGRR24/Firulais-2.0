
package controllers.user;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.RendezvousService;
import services.RequestService;
import services.ServicceService;
import controllers.AbstractController;
import domain.Request;
import domain.Servicce;

@Controller
@RequestMapping("/request/user")
public class RequestUserController extends AbstractController {

	//Servicce------------------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private RequestService		requestService;

	@Autowired
	private ServicceService		serviceService;

	@Autowired
	private LoginService		loginService;


	//Constructor---------------------------------------------------------------------

	public RequestUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {
		Assert.isTrue(this.rendezvousService.findOne(rendezvousId).getUser().getId() == this.loginService.getPrincipalActor().getId());
		final ModelAndView res = new ModelAndView("request/list");
		final Collection<Request> r = this.requestService.requestsByRendezvous(rendezvousId);
		res.addObject("requests", r);
		res.addObject("requestURI", "request/user/list.do");
		res.addObject("rendezvousId", rendezvousId);
		return res;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Request e;

		e = this.requestService.create(this.rendezvousService.findOne(rendezvousId));
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int requestId) {
		ModelAndView result;
		Request e;

		e = this.requestService.findOne(requestId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Request e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.requestService.save(e);
				result = new ModelAndView("redirect:/request/user/list.do?rendezvousId=" + e.getRendezvous().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Request e, final BindingResult binding) {
		ModelAndView result;

		try {
			this.requestService.delete(e);
			result = new ModelAndView("redirect:/request/user/list.do?rendezvousId=" + e.getRendezvous().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(e, "commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request e, final String message) {
		Assert.isTrue(e.getRendezvous().getUser().getId() == this.loginService.getPrincipalActor().getId());
		ModelAndView result;

		result = new ModelAndView("request/edit");
		result.addObject("request", e);
		result.addObject("message", message);
		final List<Servicce> servicces = (List<Servicce>) this.serviceService.findAll();
		final List<Servicce> serviccesVisibles = (List<Servicce>) this.serviceService.serviccesVisibles();

		servicces.removeAll(this.serviceService.servicesByRendezvous(e.getRendezvous().getId()));

		result.addObject("srvcs", servicces);

		result.addObject("serviccesVisibles", serviccesVisibles);

		result.addObject("requestURI", "request/user/edit.do");

		return result;
	}
}
