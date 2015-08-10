package be.vdab.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.restclients.TemperaturenClient;

@Service
public class TempServiceImpl implements TempService {
	private final TemperaturenClient temperaturenClient;

	@Autowired
	TempServiceImpl(TemperaturenClient temperaturenClient) {
		this.temperaturenClient = temperaturenClient;
	}

	@Override
	public BigDecimal getTemp(String gemeente) {
		return temperaturenClient.getTemperatuur(gemeente);
	}
}
