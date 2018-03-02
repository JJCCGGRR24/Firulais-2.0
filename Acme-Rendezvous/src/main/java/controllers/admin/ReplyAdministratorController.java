
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReplyService;
import controllers.AbstractController;
import domain.Reply;

@Controller
@RequestMapping("/reply/administrator")
public class ReplyAdministratorController extends AbstractController {

	//Service------------------------------------------------------------------------

	@Autowired
	private ReplyService	replyService;


	//Constructor---------------------------------------------------------------------

	public ReplyAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int commentId) {

		final ModelAndView res = new ModelAndView("reply/list");

		final Collection<Reply> r = this.replyService.repliesByComment(commentId);

		res.addObject("cmmntId", commentId);

		res.addObject("replies", r);

		res.addObject("requestURI", "reply/administrator/list.do");
		return res;
	}

}
