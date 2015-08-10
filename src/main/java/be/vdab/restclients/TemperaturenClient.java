package be.vdab.restclients;

import java.math.BigDecimal;

public interface TemperaturenClient {
	BigDecimal getTemperatuur(String gemeente);
}
