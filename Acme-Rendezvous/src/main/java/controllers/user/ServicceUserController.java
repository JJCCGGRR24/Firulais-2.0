
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
@RequestMapping("/servicce/user")
public class ServicceUserController extends AbstractController {

	//Servicce------------------------------------------------------------------------

	@Autowired
	private ServicceService	servicceService;


	//Constructor---------------------------------------------------------------------

	public ServicceUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("servicce/list");
		final Collection<Servicce> s = this.servicceService.findAll();
		res.addObject("servicces", s);
		res.addObject("requestURI", "servicce/user/list.do");
		return res;
	}
}
