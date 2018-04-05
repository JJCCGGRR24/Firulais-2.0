
package admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller()
@RequestMapping("/newspaper/admin")
public class NewspaperAdminController extends AbstractController {

	//Services
	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private UserService			userService;


	//Constructor
	public NewspaperAdminController() {
		super();
	}

	//List

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int newspaperId) {
		ModelAndView res = new ModelAndView();
		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);
		try {

			this.newspaperService.delete(newspaper);
			res = new ModelAndView("redirect: /newspaper/list.do");

		} catch (final Throwable oops) {
			res = this.createEditModelAndView(newspaper, "commit.error");
		}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Newspaper r) {
		ModelAndView result;
		result = this.createEditModelAndView(r, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Newspaper r, final String message) {
		ModelAndView result;
		result = new ModelAndView("newspaper/list");
		result.addObject("message", message);
		return result;
	}
}
