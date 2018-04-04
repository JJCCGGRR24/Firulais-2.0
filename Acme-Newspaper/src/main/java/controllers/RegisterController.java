
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


	//Constructor---------------------------------------------------------------------

	public RegisterController() {
		super();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView r = new ModelAndView();
		final RegisterForm registerForm = new RegisterForm();
		r = this.createEditModelAndView(registerForm);
		return r;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RegisterForm registerForm, final BindingResult bindingResult) {
		ModelAndView res = new ModelAndView();
		final User user = this.userService.reconstruct(registerForm);

		if (bindingResult.hasErrors())
			res = this.createEditModelAndView(registerForm, "commit.error");
		else if (!(registerForm.getConfirmPassword().equals(registerForm.getPassword())))
			res = this.createEditModelAndView(registerForm, "commit.password.error");
		else if (!(registerForm.isCheck()))
			res = this.createEditModelAndView(registerForm, "commit.check.error");
		else
			try {

				this.userService.save(user);
				res = new ModelAndView("redirect: /welcome/index.do");

			} catch (final DataIntegrityViolationException oops) {
				res = this.createEditModelAndView(registerForm, "error.username");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(registerForm, "commit.error");
			}
		return res;
	}
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
}
