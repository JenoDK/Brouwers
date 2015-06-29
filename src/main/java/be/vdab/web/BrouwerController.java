package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/brouwers")
class FiliaalController {
	private static final String brouwers_VIEW = "brouwers/brouwers";
	private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
	private static final String BROUWERS_OP_NAAM_VIEW = "brouwers/beginnaam";

	@RequestMapping(method = RequestMethod.GET)
	String findAll() {
		return brouwers_VIEW;
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	String createForm() {
		return TOEVOEGEN_VIEW;
	}
	
	@RequestMapping(value = "beginnaam", method = RequestMethod.GET)
	String findAllByName() {
		return BROUWERS_OP_NAAM_VIEW;
	}
}
