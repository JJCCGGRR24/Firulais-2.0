
package controllers.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import controllers.AbstractController;
import domain.Question;
import domain.Rendezvous;

@Controller
@RequestMapping("/question/all")
public class AllQuestionController extends AbstractController {

	//Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors -----------------------------------------------------------
	public AllQuestionController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {
		final ModelAndView res = new ModelAndView("question/list");
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		final List<Question> l = r.getQuestions();
		res.addObject("questions", l);
		res.addObject("rendezvous", r);
		res.addObject("requestURI", "question/all/list.do");
		return res;
	}
}
