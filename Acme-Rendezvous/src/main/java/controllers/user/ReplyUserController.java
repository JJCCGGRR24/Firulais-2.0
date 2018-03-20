
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

import services.CommentService;
import services.ReplyService;
import controllers.AbstractController;
import domain.Comment;
import domain.Reply;

@Controller
@RequestMapping("/reply/user")
public class ReplyUserController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private ReplyService	replyService;

	//	@Autowired
	//	private RendezvousService	rendezvousService;
	//
	//	@Autowired
	//	private LoginService		loginService;

	@Autowired
	private CommentService	commentService;


	//Constructor---------------------------------------------------------------------

	public ReplyUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int commentId) {

		final ModelAndView res = new ModelAndView("reply/list");

		final Collection<Reply> r = this.replyService.repliesByComment(commentId);

		res.addObject("cmmntId", commentId);

		res.addObject("replies", r);

		//res.addObject("comentable", this.rendezvousService.findOne(rendezvousId).getUsers().contains(this.loginService.getPrincipalActor()));

		res.addObject("requestURI", "reply/user/list.do");
		return res;
	}

	//	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int commentId) {
		ModelAndView result;
		Reply e;
		final Comment r = this.commentService.findOne(commentId);

		e = this.replyService.create(r);
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEndorser(@RequestParam final int replyId) {
		ModelAndView result;
		Reply e;

		e = this.replyService.findOne(replyId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEndorser(@Valid final Reply e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.replyService.save(e);
				result = new ModelAndView("redirect:/reply/user/list.do?commentId=" + e.getComment().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "general.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reply e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reply e, final String message) {
		ModelAndView result;

		result = new ModelAndView("reply/edit");
		result.addObject("reply", e);
		result.addObject("message", message);

		result.addObject("requestURI", "reply/user/edit.do");

		return result;
	}

}
