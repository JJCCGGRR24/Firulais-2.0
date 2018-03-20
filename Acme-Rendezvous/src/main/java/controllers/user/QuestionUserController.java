
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
import services.QuestionService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Question;
import domain.Rendezvous;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	//Services ---------------------------------------------------------------
	@Autowired
	private QuestionService		questionService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------
	public QuestionUserController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final int rendezvousId) {
		final ModelAndView res = new ModelAndView("question/list");
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		final List<Question> l = r.getQuestions();
		res.addObject("questions", l);
		res.addObject("editable", this.loginService.getPrincipalActor().getId() == r.getUser().getId());
		res.addObject("rendezvous", r);
		res.addObject("requestURI", "question/user/list.do");
		return res;
	}

	// Create ---------------------------------------------------------------
	@RequestMapping("/create")
	public ModelAndView create(@RequestParam final int rendezvousId) {
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		final ModelAndView res = this.createEditModelAndViewQuestion(this.questionService.create(rendezvous));
		return res;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editQuestion(@RequestParam final int questionId) {
		ModelAndView result;
		final Question question = this.questionService.findOne(questionId);
		Assert.notNull(question);
		result = this.createEditModelAndViewQuestion(question);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveQuestion(@Valid final Question question, final BindingResult binding) {
		ModelAndView result;
		final int userId = this.loginService.getPrincipalActor().getId();
		if (question.getId() != 0) {
			final Question questionBBDD = this.questionService.findOne(question.getId());
			Assert.notNull(questionBBDD);
			Assert.isTrue(questionBBDD.getRendezvous().getUser().getId() == userId);
		}
		Assert.isTrue(question.getRendezvous().getUser().getId() == userId);
		if (binding.hasErrors())
			result = this.createEditModelAndViewQuestion(question);
		else
			try {
				this.questionService.save(question);
				result = new ModelAndView("redirect:list.do?rendezvousId=" + question.getRendezvous().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndViewQuestion(question, "commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteQuestion(final Question question, final BindingResult binding) {
		ModelAndView result;
		final int userId = this.loginService.getPrincipalActor().getId();
		final Question questionBBDD = this.questionService.findOne(question.getId());
		Assert.notNull(questionBBDD);
		Assert.isTrue(questionBBDD.getRendezvous().getUser().getId() == userId);
		try {
			this.questionService.delete(question);
			result = new ModelAndView("redirect:list.do?rendezvousId=" + question.getRendezvous().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewQuestion(question, "commit.error");
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------------
	protected ModelAndView createEditModelAndViewQuestion(final Question question) {
		ModelAndView result;

		result = this.createEditModelAndViewQuestion(question, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewQuestion(final Question question, final String message) {
		ModelAndView result;
		Assert.isTrue(question.getRendezvous().getUser().getId() == this.loginService.getPrincipalActor().getId());
		result = new ModelAndView("question/edit");
		result.addObject("question", question);
		result.addObject("message", message);

		return result;
	}
}
