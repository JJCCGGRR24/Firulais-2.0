
package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ArticleService;
import controllers.AbstractController;

@Controller
@RequestMapping("/rendezvous/admin")
public class ArticleAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------------------

	@Autowired
	private ArticleService	articleService;


	//Constructor--------------------------------------------------------------------

	public ArticleAdministratorController() {
		super();
	}

	//Listing------------------------------------------------------------------------

	//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//	public ModelAndView delete(@RequestParam final int articleId) {
	//		Método pasado a ArticleController.java
	//	}
}
