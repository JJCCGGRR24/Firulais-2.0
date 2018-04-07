
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/all")
public class AllNewspaperController extends AbstractController {

	//Services ---------------------------------------------------------------
	@Autowired
	private NewspaperService	newspaperService;


	// Constructors -----------------------------------------------------------
	public AllNewspaperController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String search) {
		final ModelAndView res = new ModelAndView("newspaper/list");
		final List<Newspaper> l;
		if (search != null)
			l = (List<Newspaper>) this.newspaperService.search(search);
		else
			l = (this.newspaperService.getPublishedNewspapers());
		res.addObject("newspapers", l);

		res.addObject("requestURI", "newspaper/all/list.do");

		return res;
	}

}
