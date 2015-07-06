package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Beginnaam;

public interface BrouwerDAO {
	void create(Brouwer brouwer);

	List<Brouwer> findAll();

	List<Brouwer> findByNaam(String beginNaam);
	
	List<Brouwer> findByBeginnaam(Beginnaam beginnaam);
}
