package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.Beginnaam;

@Controller
@RequestMapping("/brouwers")
class BrouwerController {
	private static final String brouwers_VIEW = "brouwers/brouwers";
	private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
	// private static final String BROUWERS_OP_NAAM_VIEW = "brouwers/beginnaam";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/brouwers";
	private static final String ALFABET_VIEW = "brouwers/opalfabet";
	private static final String BEGINNAAM_VIEW = "brouwers/beginnaam";
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
	ModelAndView findAll(@PageableDefault(sort = { "naam" }, size = 5) Pageable pageable) {
		return new ModelAndView(brouwers_VIEW, "page",
				brouwerService.findAll(pageable));
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "brouwer", new Brouwer());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Brouwer brouwer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		brouwerService.create(brouwer);
		return REDIRECT_URL_NA_TOEVOEGEN;
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

	@RequestMapping(value = "beginnaam", method = RequestMethod.GET)
	ModelAndView findByBeginnaam() {
		Beginnaam beginnaam = new Beginnaam();
		return new ModelAndView(BEGINNAAM_VIEW).addObject(beginnaam);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "beginnaam" })
	ModelAndView findByBeginnaam(@ModelAttribute Beginnaam beginnaam,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(BEGINNAAM_VIEW);
		if (!bindingResult.hasErrors()) {
			List<Brouwer> brouwers = brouwerService.findByBeginnaam(beginnaam);
			if (brouwers.isEmpty()) {
				bindingResult.reject("geenBrouwers");
			} else {
				modelAndView.addObject("brouwers", brouwers);
			}
		}
		return modelAndView;
	}

	@InitBinder("beginnaam")
	void initBinderPostcodeReeks(DataBinder dataBinder) {
		dataBinder.setRequiredFields("beginnaam");
	}

	@InitBinder("brouwer")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

}
