
package controllers.manager;

import java.util.Collection;

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
import services.CategoryService;
import services.ServicceService;
import controllers.AbstractController;
import domain.Manager;
import domain.Servicce;

@Controller
@RequestMapping("/servicce/manager")
public class ServicceManagerController extends AbstractController {

	//Services-------------------------------------------------------------------------

	@Autowired
	private ServicceService	servicceService;

	@Autowired
	private LoginService	loginService;

	@Autowired
	private CategoryService	categoryService;


	//Constructor----------------------------------------------------------------------

	public ServicceManagerController() {
		super();
	}

	//List ------------------------------------------------------------------------
	@RequestMapping("/allServicces")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("servicce/list");
		final Collection<Servicce> servicces = this.servicceService.findAll();
		res.addObject("servicces", servicces);
		res.addObject("requestURI", "servicce/manager/allServicces.do");
		return res;
	}

	@RequestMapping("/myservicces")
	public ModelAndView myservicces() {
		final ModelAndView res = new ModelAndView("servicce/list");
		final Collection<Servicce> servicces = ((Manager) this.loginService.getPrincipalActor()).getServicces();
		res.addObject("servicces", servicces);
		res.addObject("requestURI", "servicce/manager/allServicces.do");
		return res;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Servicce e;
		e = this.servicceService.create();
		e.setManager((Manager) this.loginService.getPrincipalActor());
		result = this.createEditModelAndView(e);
		return result;
	}
	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEndorser(@RequestParam final int servicceId) {
		ModelAndView result;
		Servicce e;
		e = this.servicceService.findOne(servicceId);
		Assert.notNull(e);
		Assert.isTrue(!e.getCancelled());
		result = this.createEditModelAndView(e);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEndorser(@Valid final Servicce e, final BindingResult binding) {
		ModelAndView result;
		Assert.isTrue(!e.getCancelled());
		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.servicceService.save(e);
				result = new ModelAndView("redirect:/servicce/manager/myservicces.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteEndorser(final Servicce e, final BindingResult binding) {
		ModelAndView result;
		Assert.isTrue(!e.getCancelled());
		try {
			this.servicceService.delete(e);
			result = new ModelAndView("redirect:/servicce/manager/myservicces.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(e, "commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Servicce e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Servicce e, final String message) {
		ModelAndView result;

		result = new ModelAndView("servicce/edit");
		result.addObject("servicce", e);
		result.addObject("message", message);
		result.addObject("requestURI", "servicce/manager/edit.do");
		result.addObject("categories", this.categoryService.findAll());

		return result;
	}

}
