
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private CommentService	commentService;


	//Constructor---------------------------------------------------------------------

	public CommentAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {

		final ModelAndView res = new ModelAndView("comment/list");

		final Collection<Comment> c = this.commentService.commentsByRendezvous(rendezvousId);

		res.addObject("rendId", rendezvousId);

		res.addObject("comments", c);

		res.addObject("requestURI", "comment/administrator/list.do");
		return res;
	}

	//	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEndorser(@RequestParam final int commentId) {
		ModelAndView result;
		Comment e;

		e = this.commentService.findOne(commentId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/delete")
	public ModelAndView deleteEndorser(@RequestParam final int commentId) {

		ModelAndView result;
		final Comment r = this.commentService.findOne(commentId);
		this.commentService.delete(r);

		result = new ModelAndView("redirect:list.do?rendezvousId=" + r.getRendezvous().getId());
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

		result.addObject("requestURI", "comment/administrator/edit.do");

		return result;
	}

}
