
package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import controllers.AbstractController;
import domain.Configuration;

@Controller
@RequestMapping("/configuration/admin")
public class ConfigurationAdministratorController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private ConfigurationService	configService;


	//Constructor---------------------------------------------------------------------

	public ConfigurationAdministratorController() {
		super();
	}

	//	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Configuration e;

		e = this.configService.find();
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Configuration c, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(c);
		else
			try {
				this.configService.save(c);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(c, "general.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Configuration e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Configuration e, final String message) {
		ModelAndView result;

		result = new ModelAndView("configuration/edit");
		result.addObject("configuration", e);
		result.addObject("message", message);

		return result;
	}

}
