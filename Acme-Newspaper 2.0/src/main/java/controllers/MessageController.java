
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FolderService;
import services.MessageService;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private MessageService	messageService;

	@Autowired
	private FolderService	folderService;


	//Constructor--------------------------------------------------------------------

	public MessageController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//	public ModelAndView list() {
	//		final ModelAndView modelAndView;
	//
	//		final Collection<Advertisement> advertisements = this.advertisementService.getAdvertisementsWithTabbooWords();
	//
	//		modelAndView = new ModelAndView("advertisement/list");
	//		modelAndView.addObject("advertisements", advertisements);
	//		modelAndView.addObject("requestURI", "advertisement/admin/list.do");
	//
	//		return modelAndView;
	//	}

	@RequestMapping(value = "/sent", method = RequestMethod.POST, params = "save")
	public ModelAndView sent(@RequestParam final MessageForm messageForm, final BindingResult bindingResult) {

		final Message a = this.messageService.reconstruct(messageForm);
		ModelAndView res;

		if (bindingResult.hasErrors())
			res = this.createEditModelAndView(messageForm, "commit.error");

		try {
			this.messageService.sendMessage(a);
			res = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(messageForm, "commit.error");
		}

		return res;
	}

	protected ModelAndView createEditModelAndView(final MessageForm r) {
		ModelAndView result;
		result = this.createEditModelAndView(r, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageForm a, final String message) {
		ModelAndView result;
		result = new ModelAndView("message/sent");
		result.addObject("message", message);
		return result;
	}
}
