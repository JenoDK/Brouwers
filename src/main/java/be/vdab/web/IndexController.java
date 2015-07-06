package be.vdab.web;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index(Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		int uur = calendar.get(Calendar.HOUR_OF_DAY);
		String begroetingMoment = null;
		if (uur >= 0 && uur <= 5) {
			begroetingMoment = "Nacht";
		} else if (uur >= 6 && uur <= 11) {
			begroetingMoment = "Ochtend";
		} else if (uur >= 12 && uur <= 17) {
			begroetingMoment = "Middag";
		} else if (uur >= 18 && uur <= 23) {
			begroetingMoment = "Avond";
		}
		return new ModelAndView(VIEW, "moment", begroetingMoment);
	}
}
