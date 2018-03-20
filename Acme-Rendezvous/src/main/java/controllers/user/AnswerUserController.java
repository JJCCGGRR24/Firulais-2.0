
package controllers.user;

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
import services.QuestionService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;

@Controller
@RequestMapping("/answer/user")
public class AnswerUserController extends AbstractController {

	//Services ---------------------------------------------------------------
	@Autowired
	private AnswerService	answerService;

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------
	public AnswerUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int questionId) {
		final ModelAndView res = new ModelAndView("answer/list");
		final Question r = this.questionService.findOne(questionId);
		final List<Answer> l = r.getAnswers();
		res.addObject("answers", l);
		res.addObject("creable", r.getRendezvous().getUsers().contains(this.loginService.getPrincipalActor()));
		res.addObject("question", r);
		res.addObject("requestURI", "answer/user/list.do");
		return res;
	}

	//	@RequestMapping(value = "/editlist")
	//	public ModelAndView editlist() {
	//		
	//		return null;
	//	}
	//
	//	@RequestMapping(value = "/editlist", method = RequestMethod.POST, params = "save")
	//	public ModelAndView editlist(@Valid final AnswersForm answerForm, final BindingResult binding) {
	//		ModelAndView result;
	//		final int userId = this.loginService.getPrincipalActor().getId();
	//		Assert.isTrue(answer.getUser().getId() == userId);
	//		Assert.isTrue(answer.getId() == 0);
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndViewAnswer(answer);
	//		else
	//			try {
	//				this.answerService.save(answer);
	//				result = new ModelAndView("redirect:list.do?questionId=" + answer.getQuestion().getId());
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndViewAnswer(answer, "commit.error");
	//			}
	//		return result;
	//	}

	// Create ---------------------------------------------------------------
	@RequestMapping("/create")
	public ModelAndView create(@RequestParam final int questionId) {
		final Question question = this.questionService.findOne(questionId);
		Assert.notNull(question);
		final ModelAndView res = this.createEditModelAndViewAnswer(this.answerService.create(question));
		return res;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAnswer(@Valid final Answer answer, final BindingResult binding) {
		ModelAndView result;
		final int userId = this.loginService.getPrincipalActor().getId();
		Assert.isTrue(answer.getUser().getId() == userId);
		Assert.isTrue(answer.getId() == 0);
		if (binding.hasErrors())
			result = this.createEditModelAndViewAnswer(answer);
		else
			try {
				this.answerService.save(answer);
				result = new ModelAndView("redirect:list.do?questionId=" + answer.getQuestion().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndViewAnswer(answer, "commit.error");
			}
		return result;
	}
	// Ancillary methods ---------------------------------------------------------------
	protected ModelAndView createEditModelAndViewAnswer(final Answer answer) {
		ModelAndView result;

		result = this.createEditModelAndViewAnswer(answer, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewAnswer(final Answer answer, final String message) {
		ModelAndView result;
		Assert.isTrue(answer.getUser().getId() == this.loginService.getPrincipalActor().getId());
		result = new ModelAndView("answer/edit");
		result.addObject("answer", answer);
		result.addObject("message", message);

		return result;
	}
}
