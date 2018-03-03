
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	//Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;

	@Autowired
	private CommentService		commentService;


	// Constructors -----------------------------------------------------------
	public DashboardAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("dashboard/display");
		res.addObject("queryC1", this.userService.queryC1());
		res.addObject("queryC2", this.userService.queryC2());
		res.addObject("queryC3", this.rendezvousService.queryC3());
		res.addObject("queryC4", this.userService.queryC4());
		res.addObject("queryC5", this.rendezvousService.queryC5());
		res.addObject("queryB1", this.rendezvousService.queryB1());
		res.addObject("queryB2", this.rendezvousService.queryB2());
		res.addObject("queryB3", this.rendezvousService.queryB3());
		res.addObject("queryA1", this.rendezvousService.queryA1());
		res.addObject("queryA2", this.rendezvousService.queryA2());
		res.addObject("queryA3", this.commentService.queryA3());

		return res;
	}
}
