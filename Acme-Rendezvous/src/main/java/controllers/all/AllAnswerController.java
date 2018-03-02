
package controllers.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;

@Controller
@RequestMapping("/answer/all")
public class AllAnswerController extends AbstractController {

	//Services ---------------------------------------------------------------

	@Autowired
	private QuestionService	questionService;


	// Constructors -----------------------------------------------------------
	public AllAnswerController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int questionId) {
		final ModelAndView res = new ModelAndView("answer/list");
		final Question q = this.questionService.findOne(questionId);
		final List<Answer> l = q.getAnswers();
		res.addObject("answers", l);
		res.addObject("question", q);
		res.addObject("requestURI", "answer/all/list.do");
		return res;
	}
}
