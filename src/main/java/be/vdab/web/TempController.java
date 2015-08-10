package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.exceptions.KanTemperatureNietLezenException;
import be.vdab.services.TempService;

@Controller
@RequestMapping("temp")
public class TempController {
	private final static String VIEW = "temp/temperatuur";
	private final TempService tempService;

	@Autowired
	TempController(TempService tempService) {
		this.tempService = tempService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "{gemeente}/temperatuur")
	ModelAndView getTemp(@PathVariable String gemeente) {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		try {
			modelAndView.addObject("temp", tempService.getTemp(gemeente));
		} catch (KanTemperatureNietLezenException ex) {
			modelAndView.addObject("fout", "Kan temp niet lezen");
		}
		return modelAndView;
	}
}
