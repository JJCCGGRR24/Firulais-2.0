
package controllers.admin;

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

import services.ArticleService;
import services.ChirpService;
import services.NewspaperService;
import services.TabooService;
import controllers.AbstractController;
import domain.Article;
import domain.Chirp;
import domain.Newspaper;
import domain.Taboo;

@Controller
@RequestMapping("/taboo/admin")
public class TabooAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private TabooService		tabooService;

	@Autowired
	private ChirpService		chirpService;

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ArticleService		articleService;


	//Constructor--------------------------------------------------------------------

	public TabooAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result = new ModelAndView("taboo/list");
		final List<Taboo> tw = (List<Taboo>) this.tabooService.findAll();
		result.addObject("requestURI", "taboo/admin/list.do");
		result.addObject("taboos", tw);
		return result;
	}

	@RequestMapping(value = "/listChirpTabooWord", method = RequestMethod.GET)
	public ModelAndView listChirpTabooWord() {
		final ModelAndView result = new ModelAndView("chirp/list");
		final List<Chirp> tw = this.chirpService.getChirpsTabooWords();
		result.addObject("requestURI", "taboo/admin/listChirpTabooWord.do");
		result.addObject("chirps", tw);
		return result;
	}

	@RequestMapping(value = "/listNewspaperTabooWord", method = RequestMethod.GET)
	public ModelAndView listNewspaperTabooWord() {
		final ModelAndView result = new ModelAndView("newspaper/list");
		final List<Newspaper> tw = this.newspaperService.getNewspaperTabooWords();
		result.addObject("requestURI", "taboo/admin/listNewspaperTabooWord.do");
		result.addObject("newspapers", tw);
		return result;
	}

	@RequestMapping(value = "/listArticleTabooWord", method = RequestMethod.GET)
	public ModelAndView listArticlesTabooWord() {
		final ModelAndView result = new ModelAndView("article/list");
		final List<Article> tw = this.articleService.getArticlesTabooWords();
		result.addObject("requestURI", "taboo/admin/listArticleTabooWord.do");
		result.addObject("articles", tw);
		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Taboo e;

		e = this.tabooService.create();
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editTaboo(@RequestParam final int tabooId) {
		ModelAndView result;
		Taboo e;

		e = this.tabooService.findOne(tabooId);
		Assert.notNull(e);
		result = this.createEditModelAndView(e);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveTaboo(@Valid final Taboo e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				this.tabooService.save(e);
				result = new ModelAndView("redirect:/taboo/admin/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(e, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteTaboo(final Taboo e, final BindingResult binding) {
		ModelAndView result;

		try {
			this.tabooService.delete(e);
			result = new ModelAndView("redirect:/taboo/admin/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(e, "general.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Taboo e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Taboo e, final String message) {
		ModelAndView result;

		result = new ModelAndView("taboo/edit");
		result.addObject("taboo", e);
		result.addObject("message", message);

		result.addObject("requestURI", "taboo/admin/edit.do");

		return result;
	}
}
