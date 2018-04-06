
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.NewspaperService;
import controllers.AbstractController;

@Controller
@RequestMapping("/newspaper/admin")
public class NewspaperAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private NewspaperService	newspaperService;


	//Constructor--------------------------------------------------------------------

	public NewspaperAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//	public ModelAndView delete(@RequestParam final int newspaperId) {
	//		Método pasado a NewspaperController.java
	//	}

}
