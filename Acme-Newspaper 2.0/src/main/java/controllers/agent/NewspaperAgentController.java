/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.NewspaperService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/agent")
public class NewspaperAgentController extends AbstractController {

	// Managed repository -----------------------------------------------------
	@Autowired
	private NewspaperService	newspaperService;

	//	@Autowired
	//	private AgentService		agentService;

	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------

	public NewspaperAgentController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		final List<Newspaper> newspapers = this.newspaperService.findByAgent(this.loginService.getPrincipalActor().getId());
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("requestURI", "newspaper/agent/list.do");
		return result;
	}

	@RequestMapping("/noList")
	public ModelAndView noList() {
		ModelAndView result;
		final List<Newspaper> newspapers = this.newspaperService.findByNoAgent(this.loginService.getPrincipalActor().getId());
		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("requestURI", "newspaper/agent/noList.do");
		return result;
	}

}
