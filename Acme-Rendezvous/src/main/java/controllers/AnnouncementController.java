
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import domain.Announcement;
import domain.Rendezvous;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {

	//Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors -----------------------------------------------------------
	public AnnouncementController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {
		final ModelAndView res = new ModelAndView("announcement/list");
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		final List<Announcement> a = rendezvous.getAnnouncements();
		res.addObject("announcements", a);
		res.addObject("requestURI", "announcement/list.do");
		res.addObject("rendezvousId", rendezvousId);
		res.addObject("rendezvous", rendezvous);
		return res;
	}
}
