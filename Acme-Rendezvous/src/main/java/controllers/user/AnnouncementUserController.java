
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.AnnouncementService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/announcement/user")
public class AnnouncementUserController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private LoginService		loginService;


	//Constructor---------------------------------------------------------------------

	public AnnouncementUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {

		final ModelAndView res = new ModelAndView("announcement/list");
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.isTrue(rendezvous.getUsers().contains(this.loginService.getPrincipalActor()));
		final Collection<Announcement> announcements = this.announcementService.getAnnouncementsByRendezvousId(rendezvousId);

		res.addObject("announcements", announcements);
		res.addObject("rendezvous", rendezvous);
		res.addObject("requestURI", "announcement/user/list.do");
		return res;
	}
	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		final ModelAndView modelAndView;

		final Rendezvous rendevous = this.rendezvousService.findOne(rendezvousId);
		final Announcement announcement = this.announcementService.create(rendevous);

		modelAndView = this.createEditModelAndView(announcement);

		return modelAndView;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Announcement announcement, final BindingResult bindingResult) {
		ModelAndView modelAndView;

		if (bindingResult.hasErrors())
			modelAndView = this.createEditModelAndView(announcement);
		else
			try {
				this.announcementService.save(announcement);
				modelAndView = new ModelAndView("redirect:/announcement/user/list.do?rendezvousId=" + announcement.getRendezvous().getId());
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

		result = new ModelAndView("announcement/edit");
		result.addObject("announcement", announcement);
		result.addObject("message", message);
		result.addObject("requestURI", "announcement/user/edit.do");
		Assert.isTrue(((User) this.loginService.getPrincipalActor()).equals(announcement.getRendezvous().getUser()));

		return result;
	}

}
