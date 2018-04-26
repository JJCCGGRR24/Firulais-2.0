
package controllers.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import controllers.AbstractController;
import domain.Message;
import forms.BroadcastForm;

@Controller
@RequestMapping("/message/admin")
public class MessageAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private MessageService	messageService;


	//Constructor--------------------------------------------------------------------

	public MessageAdministratorController() {
		super();
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView createBbroadcast() {
		ModelAndView result;
		final BroadcastForm e = new BroadcastForm();
		result = this.createEditModelAndView(e);

		return result;
	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "save")
	public ModelAndView saveBroadcast(@Valid final BroadcastForm e, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(e);
		else
			try {
				final Message m = this.messageService.sendBroadcast(e);
				result = new ModelAndView("redirect:/message/admin/list.do?messageId=" + m.getId());
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("Palabra message repetida"))
					result = this.createEditModelAndView(e, "message.repeat");
				else
					result = this.createEditModelAndView(e, "general.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final BroadcastForm e) {
		ModelAndView result;

		result = this.createEditModelAndView(e, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final BroadcastForm e, final String message) {
		ModelAndView result;

		result = new ModelAndView("message/broadcast");
		result.addObject("broadcastForm", e);
		result.addObject("message", message);
		final List<String> ps = new ArrayList<String>();
		ps.add("LOW");
		ps.add("NEUTRAL");
		ps.add("HIGH");
		result.addObject("ps", ps);
		result.addObject("requestURI", "message/admin/broadcast.do");

		return result;
	}
}
