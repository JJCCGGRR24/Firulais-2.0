
package controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Newspaper;
import services.AdministratorService;

@Controller
@RequestMapping("/admin")
public class DashboardAdministratorController extends AbstractController {

	//Service------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;


	//Constructor--------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	//DASHBOARD----------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView modelAndView;
		modelAndView = new ModelAndView("administrator/dashboard");

		final Double[] queryC1 = this.administratorService.queryC1();
		modelAndView.addObject("queryC1", queryC1);

		final Double[] queryC2 = this.administratorService.queryC2();
		modelAndView.addObject("queryC2", queryC2);

		final Double[] queryC3 = this.administratorService.queryC3();
		modelAndView.addObject("queryC3", queryC3);

		final List<Newspaper> queryC4 = this.administratorService.queryC4();
		modelAndView.addObject("queryC4", queryC4);

		final List<Newspaper> queryC5 = this.administratorService.queryC5();
		modelAndView.addObject("queryC5", queryC5);

		final Double queryC6 = this.administratorService.queryC6();
		modelAndView.addObject("queryC6", queryC6);

		final Double queryC7 = this.administratorService.queryC7();
		modelAndView.addObject("queryC7", queryC7);

		final Double queryB1 = this.administratorService.queryB1();
		modelAndView.addObject("queryB1", queryB1);

		final Double queryB2 = this.administratorService.queryB2();
		modelAndView.addObject("queryB2", queryB2);

		final Double queryB3 = this.administratorService.queryB3();
		modelAndView.addObject("queryB3", queryB3);

		final Double[] queryB4 = this.administratorService.queryB4();
		modelAndView.addObject("queryB4", queryB4);

		final Double queryB5 = this.administratorService.queryB5();
		modelAndView.addObject("queryB5", queryB5);

		final Double queryA1 = this.administratorService.queryA1();
		modelAndView.addObject("queryA1", queryA1);

		final Double queryA2 = this.administratorService.queryA2();
		modelAndView.addObject("queryA2", queryA2);

		final Double queryA3 = this.administratorService.queryA3();
		modelAndView.addObject("queryA3", queryA3);

		final Double queryA4 = this.administratorService.queryA4();
		modelAndView.addObject("queryA4", queryA4);

		final Double queryA5 = this.administratorService.queryA5();
		modelAndView.addObject("queryA5", queryA5);

		return modelAndView;
	}
}
