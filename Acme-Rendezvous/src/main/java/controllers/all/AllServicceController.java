
package controllers.all;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.ServicceService;
import controllers.AbstractController;
import domain.Servicce;

@Controller
@RequestMapping("/servicce/all")
public class AllServicceController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private ServicceService	servicceService;

	@Autowired
	private CategoryService	categoryService;


	//Constructor---------------------------------------------------------------------

	public AllServicceController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false) final Integer categoryId) {
		final ModelAndView res = new ModelAndView("servicce/list");
		Collection<Servicce> s = null;
		if (categoryId != null && categoryId != 0)
			s = this.categoryService.findOne(categoryId).getServicces();
		else
			s = this.servicceService.findAll();
		res.addObject("servicces", s);
		res.addObject("requestURI", "servicce/all/list.do");
		return res;
	}
}
