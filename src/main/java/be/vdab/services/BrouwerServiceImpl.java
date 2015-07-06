package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Beginnaam;

@Service
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwerDAO;

	@Autowired
	BrouwerServiceImpl(BrouwerDAO brouwerDAO) {
		this.brouwerDAO = brouwerDAO;
	}

	@Override
	public void create(Brouwer brouwer) {
		brouwerDAO.create(brouwer);

	}

	@Override
	public List<Brouwer> findAll() {
		return brouwerDAO.findAll();
	}

	@Override
	public List<Brouwer> findByNaam(String beginNaam) {
		return brouwerDAO.findByNaam(beginNaam);
	}
	
	@Override
	public List<Brouwer> findByBeginnaam(Beginnaam beginnaam){
		return brouwerDAO.findByBeginnaam(beginnaam);
	}

}
