package be.vdab.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Beginnaam;

@Repository
public class BrouwerDAOImpl implements BrouwerDAO {

	private final Map<Long, Brouwer> brouwers = new ConcurrentHashMap<>();

	BrouwerDAOImpl() {
		brouwers.put(1L, new Brouwer(1, "Moortgat", null, new Adres(
				"Oostterstraat", "15", 9000, "Gent")));
		brouwers.put(2L, new Brouwer(2, "Duvel", null, new Adres(
				"Kapellestraat", "27", 9060, "Zelzate")));
		brouwers.put(3L, new Brouwer(3, "Jupiler", null, new Adres(
				"Leegstraat", "8", 5500, "Zaventem")));
		brouwers.put(4L, new Brouwer(4, "Juzeep", null, new Adres(
				"Leegstraat", "8", 5500, "Zaventem")));
	}

	@Override
	public void create(Brouwer brouwer) {
		brouwer.setBrouwerNr(Collections.max(brouwers.keySet()) + 1);
		brouwers.put(brouwer.getBrouwerNr(), brouwer);
	}

	@Override
	public List<Brouwer> findAll() {
		return new ArrayList<Brouwer>(brouwers.values());
	}

	@Override
	public List<Brouwer> findByNaam(String beginNaam) {
		List<Brouwer> brouwersByNaam = new ArrayList<Brouwer>();
		for (Map.Entry<Long, Brouwer> entry : brouwers.entrySet()) {
			if (entry.getValue().getNaam().substring(0, 1).equals(beginNaam)) {
				brouwersByNaam.add(entry.getValue());
			}
		}
		return brouwersByNaam;
	}
	
	@Override
	public List<Brouwer> findByBeginnaam(Beginnaam beginnaam){
		List<Brouwer> brouwersByNaam = new ArrayList<Brouwer>();
		for (Brouwer brouwer : this.brouwers.values()){
			if (beginnaam.startMet(brouwer.getNaam())){
				brouwersByNaam.add(brouwer);
			}
		}
		return brouwersByNaam;
	}

}
