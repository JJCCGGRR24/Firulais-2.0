
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import controllers.AbstractController;
import domain.Announcement;

@Controller
@RequestMapping("/announcement/administrator")
public class AnnouncementAdministratorController extends AbstractController {

	//Services-------------------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;


	//Constructor----------------------------------------------------------------------

	public AnnouncementAdministratorController() {
		super();
	}

	//Editing------------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int announcementId) {
		ModelAndView modelAndView;

		final Announcement announcement = this.announcementService.findOne(announcementId);
		final int rendezvousId = announcement.getRendezvous().getId();
		try {
			this.announcementService.delete(announcement);
			modelAndView = new ModelAndView("redirect:/announcement/list.do?rendezvousId=" + rendezvousId);
		} catch (final Throwable oops) {
			modelAndView = this.createEditModelAndView(announcement, "announcement.commit.error");
		}
		return modelAndView;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView result;

		result = this.createEditModelAndView(announcement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Announcement announcement, final String message) {
		ModelAndView result;

		result = new ModelAndView("announcement/list");
		final Collection<Announcement> announcements = announcement.getRendezvous().getAnnouncements();
		result.addObject("announcements", announcements);
		result.addObject("message", message);

		return result;
	}

}
