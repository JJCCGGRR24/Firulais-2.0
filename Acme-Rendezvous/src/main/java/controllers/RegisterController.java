
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.RegisterForm;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

	//Services

	@Autowired
	private UserService	userService;


	//	@Autowired
	//	private ActorService	actorService;

	// Constructors -----------------------------------------------------------

	public RegisterController() {
		super();
	}

	//Register ----------------------------------------------------------------

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView register() {
		final ModelAndView res;
		final RegisterForm registerForm = new RegisterForm();
		res = this.createEditModelAndView(registerForm);
		return res;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid final RegisterForm rf, final BindingResult bindingResult) {
		ModelAndView res;
		final User user = this.userService.reconstruct(rf);

		if (rf.getCheck() == false)
			res = this.createEditModelAndView(rf, "check.error");
		else if (rf.getPassword().equals(rf.getPasswordConfirm())) {

			if (bindingResult.hasErrors())
				res = this.createEditModelAndView(rf);
			else
				try {
					this.userService.save(user);
					res = new ModelAndView("redirect:../welcome/index.do");
				} catch (final DataIntegrityViolationException oops) {
					System.out.println(oops.getMessage());
					res = this.createEditModelAndView(rf, "error.exists");
				} catch (final Throwable oops) {
					res = this.createEditModelAndView(rf, "commit.error");
				}
		} else
			res = this.createEditModelAndView(rf, "password.error");

		return res;

	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final RegisterForm r) {
		ModelAndView result;
		result = this.createEditModelAndView(r, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final RegisterForm r, final String message) {
		ModelAndView result;
		result = new ModelAndView("user/register");
		result.addObject("registerForm", r);
		result.addObject("message", message);
		return result;
	}

	protected String encodePass(final String s) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(s, null);
		return hash;
	}

}
