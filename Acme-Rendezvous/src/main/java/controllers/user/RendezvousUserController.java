
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import services.AnswerService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.AnswersForm;
import forms.LinkForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private AnswerService		answerService;


	//Constructor---------------------------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	//Listing-------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int userId) {
		final ModelAndView modelAndView;
		modelAndView = new ModelAndView("rendezvous/list");

		final Collection<Rendezvous> rendezvous1 = this.rendezvousService.listRSVPdPast(userId);
		final Collection<Rendezvous> rendezvous2 = this.rendezvousService.listRSVPdFuture(userId);

		if (this.userService.isUser() == true) {
			final User principal = this.userService.findByPrincipal();
			modelAndView.addObject("principal", principal);
			modelAndView.addObject("myRs", ((User) this.loginService.getPrincipalActor()).getRSVPd());
			if (this.userService.isAdult(principal)) {
				modelAndView.addObject("rendezvous1", rendezvous1);
				modelAndView.addObject("rendezvous2", rendezvous2);
			} else {
				modelAndView.addObject("rendezvous1", this.userService.isNotAdultUser(rendezvous1));
				modelAndView.addObject("rendezvous2", this.userService.isNotAdultUser(rendezvous2));
			}
		} else {
			modelAndView.addObject("rendezvous1", this.userService.isNotAdultUser(rendezvous1));
			modelAndView.addObject("rendezvous2", this.userService.isNotAdultUser(rendezvous2));
		}

		modelAndView.addObject("requestURI", "rendezvous/user/list.do?userId=" + userId);
		final List<Rendezvous> rsvp = ((User) this.loginService.getPrincipalActor()).getRSVPd();
		modelAndView.addObject("rsvp", rsvp);
		return modelAndView;

	}

	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public ModelAndView list2() {
		final ModelAndView modelAndView;
		modelAndView = new ModelAndView("rendezvous/list");

		if (this.userService.isUser() == true) {
			final User principal = this.userService.findByPrincipal();
			modelAndView.addObject("principal", principal);
		}
		final Collection<Rendezvous> rendezvous1 = this.rendezvousService.listMyPastRendezvous();
		final Collection<Rendezvous> rendezvous2 = this.rendezvousService.listMyFutureRendezvous();

		modelAndView.addObject("rendezvous1", rendezvous1);
		modelAndView.addObject("rendezvous2", rendezvous2);
		modelAndView.addObject("myRs", ((User) this.loginService.getPrincipalActor()).getRSVPd());
		modelAndView.addObject("requestURI", "rendezvous/user/mylist.do");
		final List<Rendezvous> rsvp = ((User) this.loginService.getPrincipalActor()).getRendezvouses();
		modelAndView.addObject("rsvp", rsvp);
		return modelAndView;

	}

	@RequestMapping(value = "/RSVPd", method = RequestMethod.GET)
	public ModelAndView RSVPd(@RequestParam(required = false) final String message) {
		final ModelAndView res;
		res = new ModelAndView("rendezvous/list");
		final List<Rendezvous> l = this.rendezvousService.listRSVPdPast(this.loginService.getPrincipalActor().getId());
		final List<Rendezvous> l2 = this.rendezvousService.listRSVPdFuture(this.loginService.getPrincipalActor().getId());
		res.addObject("rendezvous1", l);
		res.addObject("rendezvous2", l2);
		res.addObject("myRs", ((User) this.loginService.getPrincipalActor()).getRSVPd());
		res.addObject("requestURI", "rendezvous/user/RSVPd.do");
		res.addObject("message", message);
		final List<Rendezvous> rsvp = ((User) this.loginService.getPrincipalActor()).getRendezvouses();
		res.addObject("rsvp", rsvp);
		return res;
	}

	//Creating------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView modelAndView;

		final Rendezvous rendezvous = this.rendezvousService.create();

		modelAndView = this.createEditModelAndView(rendezvous);

		return modelAndView;

	}

	//Editing------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {
		final ModelAndView modelAndView;

		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.notNull(rendezvous);

		modelAndView = this.createEditModelAndView(rendezvous);
		Assert.isTrue(rendezvous.getFinalMode() == false);
		Assert.isTrue(rendezvous.getDeleted() == false);

		return modelAndView;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Rendezvous rendezvous, final BindingResult bindingResult) {
		ModelAndView modelAndView;
		final Date now = new Date();

		if (bindingResult.hasErrors() || !now.before(rendezvous.getMoment()))
			if (rendezvous.getMoment() != null && now.before(rendezvous.getMoment()))
				modelAndView = this.createEditModelAndView(rendezvous);
			else
				modelAndView = this.createEditModelAndView(rendezvous, "rendezvous.date.message");
		else
			try {
				this.rendezvousService.checkIsPrincipal(rendezvous);
				if (rendezvous.getId() == 0) {
					rendezvous.setUsers(new ArrayList<User>());
					rendezvous.getUsers().add((User) this.loginService.getPrincipalActor());
					this.rendezvousService.save(rendezvous);
				} else
					this.rendezvousService.update(rendezvous);
				modelAndView = new ModelAndView("redirect:/rendezvous/list.do");
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return modelAndView;

	}
	//Editing------------------------------------------------------------------------

	@RequestMapping(value = "/rsvp")
	public ModelAndView rsvp(@RequestParam final int rendezvousId, @RequestParam(required = false) final String message) {
		ModelAndView result;
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		final User u = (User) this.loginService.getPrincipalActor();
		result = new ModelAndView("answer/editlist");
		result.addObject("answersForm", this.answerService.create(r));
		result.addObject("message", message);
		result.addObject("rendezvous", r);
		if (r.getDeleted())
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.4");
		else if (r.getMoment().before(new Date()))
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.3");
		else if (r.getUsers().contains(u))
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.1");
		else if (!this.userService.isAdult(u) && r.getAdultsOnly())
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.7");
		return result;
	}

	@RequestMapping(value = "/rsvp", method = RequestMethod.POST, params = "save")
	public ModelAndView rsvp(final AnswersForm answersForm, final BindingResult bindingResult) {
		ModelAndView result;
		final Rendezvous r = this.rendezvousService.findOne(answersForm.getRid());
		final User u = (User) this.loginService.getPrincipalActor();
		result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do");
		if (r.getDeleted())
			result = new ModelAndView("redirect:/rendezvous/user/rsvp.do?rendezvouzId=" + answersForm.getRid() + "&message=error.4");
		else if (r.getMoment().before(new Date()))
			result = new ModelAndView("redirect:/rendezvous/user/rsvp.do?rendezvouzId=" + answersForm.getRid() + "&message=error.3");
		else if (r.getUsers().contains(u))
			result = new ModelAndView("redirect:/rendezvous/user/rsvp.do?rendezvouzId=" + answersForm.getRid() + "&message=error.1");
		else if (!this.userService.isAdult(u) && r.getAdultsOnly())
			result = new ModelAndView("redirect:/rendezvous/user/rsvp.do?rendezvouzId=" + answersForm.getRid() + "&message=error.7");
		else if (bindingResult.hasErrors()) {
			result = new ModelAndView("answer/editlist");
			result.addObject("answersForm", this.answerService.create(r));
			result.addObject("rendezvous", r);
		} else {
			final List<Answer> las = answersForm.getLas();
			final List<Question> lqs = r.getQuestions();
			for (int i = 0; i < answersForm.getLas().size(); i++) {
				final Answer a = las.get(i);
				if (a.getText() != null && a.getText() != "") {
					final Question q = lqs.get(i);
					a.setQuestion(q);
					a.setUser((User) this.loginService.getPrincipalActor());
					this.answerService.save(a);
				}
			}
			r.getUsers().add(u);
			this.rendezvousService.save(r);
		}
		return result;
	}

	@RequestMapping(value = "/unrsvp")
	public ModelAndView unrsvp(@RequestParam final int rendezvousId) {
		ModelAndView result;
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		final User u = (User) this.loginService.getPrincipalActor();
		result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do");
		if (r.getDeleted())
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.4");
		else if (r.getMoment().before(new Date()))
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.3");
		else if (!r.getUsers().contains(u))
			result = new ModelAndView("redirect:/rendezvous/user/RSVPd.do?message=error.2");
		else {
			r.getUsers().remove(u);
			this.rendezvousService.save(r);
		}
		return result;
	}

	@RequestMapping(value = "/link", method = RequestMethod.GET)
	public ModelAndView link(@RequestParam final int rendezvousId, @RequestParam(required = false) final String message) {
		ModelAndView result = null;

		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		Assert.isTrue(!(r == null));
		final User u = (User) this.loginService.getPrincipalActor();
		Assert.isTrue(r.getUser().getId() == u.getId());
		if (r.getDeleted())
			result = new ModelAndView("redirect:/rendezvous/user/links.do?rendezvousId=" + rendezvousId);
		else {
			result = new ModelAndView("rendezvous/link");
			final LinkForm linkForm = new LinkForm();
			linkForm.setId(r.getId());
			result.addObject("linkForm", linkForm);
			final List<Rendezvous> rs = this.rendezvousService.posibleToLink(r);
			result.addObject("rendezvouses", rs);
			result.addObject("message", message);
			result.addObject("linkable", this.rendezvousService.posibleToLink(r).size() > 0);
		}
		return result;
	}

	@RequestMapping(value = "/link", method = RequestMethod.POST, params = "save")
	public ModelAndView link(@Valid final LinkForm linkForm, final BindingResult bindingResult) {
		ModelAndView result = null;
		Rendezvous r = this.rendezvousService.findOne(linkForm.getId());
		final Rendezvous linkedR = this.rendezvousService.findOne(linkForm.getR().getId());
		Assert.isTrue(!(r == null));
		final User u = (User) this.loginService.getPrincipalActor();
		if (r.getId() == linkedR.getId() || r.getDeleted() || linkedR.getDeleted() || r.getUser().getId() != u.getId() || r.getMoment().before(new Date()) || linkedR.getMoment().before(new Date())) {
			result = new ModelAndView("redirect:/rendezvous/user/link.do?rendezvousId=" + r.getId());
			result.addObject("message", "commit.error");
		} else {
			r = this.rendezvousService.link(linkForm);
			result = new ModelAndView("redirect:/rendezvous/user/links.do?rendezvousId=" + r.getId());
			result.addObject("links", r.getLinkedTo());
		}
		return result;
	}

	@RequestMapping("/links")
	public ModelAndView links(@RequestParam final int rendezvousId) {
		final ModelAndView res = new ModelAndView("rendezvous/links");
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		res.addObject("links", r.getLinkedTo());
		res.addObject("editable", this.loginService.getPrincipalActor().getId() == r.getUser().getId());
		res.addObject("rendezvous", r);
		res.addObject("requestURI", "question/user/links.do");
		res.addObject("linkable", this.rendezvousService.posibleToLink(r).size() > 0);
		return res;
	}

	@RequestMapping("/deleteLink")
	public ModelAndView deleteLink(@RequestParam final int rendezvousId, @RequestParam final int linkedId) {
		final ModelAndView res = new ModelAndView("redirect:/rendezvous/user/links.do?rendezvousId=" + rendezvousId);
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		final Rendezvous lk = this.rendezvousService.findOne(linkedId);
		Assert.isTrue(this.loginService.getPrincipalActor().getId() == r.getUser().getId());
		Assert.isTrue(r.getMoment().after(new Date()));
		Assert.isTrue(!r.getDeleted());
		r.getLinkedTo().remove(lk);
		this.rendezvousService.save(r);
		return res;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String message) {
		ModelAndView result;

		final User user = this.userService.findByPrincipal();
		final int edad = this.userService.getAge(user.getBirthdate());

		result = new ModelAndView("rendezvous/edit");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", message);
		result.addObject("edad", edad);

		return result;
	}

}
