package be.vdab.restclients;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import be.vdab.exceptions.KanTemperatureNietLezenException;

@Component
public class TemperatuurClient implements TemperaturenClient {
	private final static Logger logger = Logger
			.getLogger(TemperatuurClient.class.getName());
	private final String tempURL;
	private final RestTemplate restTemplate;

	@Autowired
	TemperatuurClient(@Value("${openWeatherMapURL}") String tempURL,
			RestTemplate restTemplate) {
		this.tempURL = tempURL;
		this.restTemplate = restTemplate;
	}

	@Override
	public BigDecimal getTemperatuur(String gemeente) {
		try {
			Current current = restTemplate.getForObject(tempURL, Current.class, gemeente);
			return current.temperature.value;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "kan temperatuur niet lezen", ex);
			throw new KanTemperatureNietLezenException();
		}
	}

}
