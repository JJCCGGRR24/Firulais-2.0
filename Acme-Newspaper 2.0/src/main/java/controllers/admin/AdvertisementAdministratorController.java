
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import controllers.AbstractController;
import domain.Advertisement;

@Controller
@RequestMapping("/advertisement/admin")
public class AdvertisementAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private AdvertisementService	advertisementService;


	//Constructor--------------------------------------------------------------------

	public AdvertisementAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView modelAndView;

		final Collection<Advertisement> advertisements = this.advertisementService.getAdvertisementsWithTabbooWords();

		modelAndView = new ModelAndView("advertisement/list");
		modelAndView.addObject("advertisements", advertisements);
		modelAndView.addObject("requestURI", "advertisement/admin/list.do");

		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int advertisementId) {

		ModelAndView res;
		final Advertisement a = this.advertisementService.findOne(advertisementId);
		try {
			this.advertisementService.delete(a);
			res = new ModelAndView("advertisement/admin/list");
		} catch (final Throwable oops) {
			res = new ModelAndView("advertisement/admin/list");
			res.addObject("message", "chirp.commit.error");
		}

		return res;
	}

}
