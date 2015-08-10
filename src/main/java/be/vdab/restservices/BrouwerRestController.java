package be.vdab.restservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.entities.Brouwer;
import be.vdab.exceptions.BrouwerNotFoundException;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.Beginnaam;

@RestController
@RequestMapping("/brouwers")
@ExposesResourceFor(Brouwer.class)
public class BrouwerRestController {
	private final BrouwerService brouwerService;
	private final EntityLinks entityLinks;

	@Autowired
	BrouwerRestController(BrouwerService brouwerService, EntityLinks entityLinks) {
		this.brouwerService = brouwerService;
		this.entityLinks = entityLinks;
	}

	@RequestMapping(value = "{brouwer}", method = RequestMethod.GET)
	BrouwerResource read(@PathVariable Brouwer brouwer) {
		if (brouwer == null) {
			throw new BrouwerNotFoundException();
		}
		return new BrouwerResource(brouwer, entityLinks);
	}

	@ExceptionHandler(BrouwerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void brouwerNotFound() {
	}

	@RequestMapping(method = RequestMethod.GET)
	BrouwersResource findAll() {
		return new BrouwersResource(brouwerService.findAll(), entityLinks);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "beginnaam" })
	BrouwersResource findByBeginnaam(@ModelAttribute Beginnaam beginnaam) {
		List<Brouwer> brouwers = brouwerService.findByBeginnaam(beginnaam);
		if (brouwers.isEmpty()) {
			throw new BrouwerNotFoundException();
		}
		return new BrouwersResource(brouwers, entityLinks);
	}
}
