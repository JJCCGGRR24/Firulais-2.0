
package controllers.admin;

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

import services.TabooService;
import controllers.AbstractController;
import domain.Taboo;

@Controller
@RequestMapping("/taboo/admin")
public class TabooAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private TabooService	tabooService;


	//Constructor--------------------------------------------------------------------

	public TabooAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result = new ModelAndView("taboo/list");
		final List<Taboo> tw = (List<Taboo>) this.tabooService.findAll();
		result.addObject("requestURI", "taboo/admin/list.do");
		result.addObject("taboos", tw);
		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Taboo e;

		e = this.tabooService.create();
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editTaboo(@RequestParam final int tabooId) {
		ModelAndView result;
		Taboo e;

		e = this.tabooService.findOne(tabooId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveTaboo(@Valid final Taboo e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.tabooService.save(e);
				result = new ModelAndView("redirect:/taboo/admin/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteTaboo(final Taboo e, final BindingResult binding) {
		ModelAndView result;

		try {
			this.tabooService.delete(e);
			result = new ModelAndView("redirect:/taboo/admin/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(e, "general.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Taboo e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Taboo e, final String message) {
		ModelAndView result;

		result = new ModelAndView("taboo/edit");
		result.addObject("taboo", e);
		result.addObject("message", message);

		result.addObject("requestURI", "taboo/admin/edit.do");

		return result;
	}
}
