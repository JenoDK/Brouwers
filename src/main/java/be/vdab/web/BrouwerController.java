package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/brouwers")
class BrouwerController {
	private static final String brouwers_VIEW = "brouwers/brouwers";
	private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
	// private static final String BROUWERS_OP_NAAM_VIEW = "brouwers/beginnaam";
	private static final String ALFABET_VIEW = "brouwers/opalfabet";
	private final char[] alfabet = new char['Z' - 'A' + 1];
	private final BrouwerService brouwerService;

	@Autowired
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			alfabet[letter - 'A'] = letter;
		}
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

	@RequestMapping(value = "opalfabet", method = RequestMethod.GET)
	ModelAndView opAlfabetForm() {
		return new ModelAndView(ALFABET_VIEW, "alfabet", alfabet);
	}

	@RequestMapping(method = RequestMethod.GET, params = "letter")
	ModelAndView opAlfabet(@RequestParam char letter) {
		return new ModelAndView(ALFABET_VIEW).addObject("alfabet", alfabet)
				.addObject("brouwers",
						brouwerService.findByNaam(String.valueOf(letter)));
	}
}
