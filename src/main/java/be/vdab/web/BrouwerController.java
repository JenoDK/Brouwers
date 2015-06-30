package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/brouwers")
class FiliaalController {
	private static final String brouwers_VIEW = "brouwers/brouwers";
	private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
	private static final String BROUWERS_OP_NAAM_VIEW = "brouwers/beginnaam";
	private final BrouwerService brouwerService;
	
	@Autowired
	FiliaalController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(brouwers_VIEW, "brouwers",
				brouwerService.findAll());
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	String createForm() {
		return TOEVOEGEN_VIEW;
	}
	
	@RequestMapping(value = "beginnaam", method = RequestMethod.GET)
	ModelAndView findAllByName(String beginNaam) {
		return new ModelAndView(brouwers_VIEW, "brouwersByNaam",
				brouwerService.findByNaam(beginNaam));
	}
}
