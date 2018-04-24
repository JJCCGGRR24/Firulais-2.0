
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import controllers.AbstractController;
import domain.Newspaper;

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

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int newspaperId) {
		ModelAndView modelAndView;

		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);

		try {
			this.newspaperService.delete(newspaper);
			modelAndView = new ModelAndView("redirect:/newspaper/list.do");
		} catch (final Throwable throwable) {
			modelAndView = new ModelAndView("redirect:/newspaper/list.do");
			modelAndView.addObject("message", "newspaper.commit.error");
		}

		return modelAndView;
	}

}
