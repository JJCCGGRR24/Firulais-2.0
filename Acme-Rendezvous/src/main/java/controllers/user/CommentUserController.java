
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
import services.CommentService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private CommentService		commentService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private LoginService		loginService;


	//Constructor---------------------------------------------------------------------

	public CommentUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {

		final ModelAndView res = new ModelAndView("comment/list");

		final Collection<Comment> c = this.commentService.commentsByRendezvous(rendezvousId);

		res.addObject("rendId", rendezvousId);

		res.addObject("comments", c);

		res.addObject("comentable", this.rendezvousService.findOne(rendezvousId).getUsers().contains(this.loginService.getPrincipalActor()));

		res.addObject("requestURI", "comment/user/list.do");
		return res;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Comment e;
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);

		e = this.commentService.create(r);
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEndorser(@RequestParam final int commentId) {
		ModelAndView result;
		Comment e;

		e = this.commentService.findOne(commentId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEndorser(@Valid final Comment e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.commentService.save(e);
				result = new ModelAndView("redirect:/comment/user/list.do?rendezvousId=" + e.getRendezvous().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteEndorser(final Comment e, final BindingResult binding) {
		ModelAndView result;

		try {
			this.commentService.delete(e);
			result = new ModelAndView("redirect:/comment/user/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(e, "general.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment e, final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", e);
		result.addObject("message", message);

		result.addObject("requestURI", "comment/user/edit.do");

		return result;
	}

}
