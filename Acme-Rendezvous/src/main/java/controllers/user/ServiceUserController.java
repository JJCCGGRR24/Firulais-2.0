
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ServicceService;
import controllers.AbstractController;
import domain.Servicce;

@Controller
@RequestMapping("/service/user")
public class ServiceUserController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private ServicceService	serviceService;


	//Constructor---------------------------------------------------------------------

	public ServiceUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {

		final ModelAndView res = new ModelAndView("service/list");

		final Collection<Servicce> s = this.serviceService.findAll();

		res.addObject("services", s);

		res.addObject("requestURI", "service/user/list.do");
		return res;
	}

}
