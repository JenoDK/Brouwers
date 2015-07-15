package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Beginnaam;

@ReadOnlyTransactionalService
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwerDAO;

	@Autowired
	BrouwerServiceImpl(BrouwerDAO brouwerDAO) {
		this.brouwerDAO = brouwerDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
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
