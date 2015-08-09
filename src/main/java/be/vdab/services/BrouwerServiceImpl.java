package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
		brouwer.setId(brouwerDAO.save(brouwer).getId());

	}

	@Override
	public Page<Brouwer> findAll(Pageable pageable) {
		return brouwerDAO.findAll(pageable);
	}

	@Override
	public List<Brouwer> findByNaam(String beginNaam) {
		return brouwerDAO.findByNaamStartingWith(beginNaam);
	}
	
	@Override
	public List<Brouwer> findByBeginnaam(Beginnaam beginnaam){
		return brouwerDAO.findByNaamStartingWith(beginnaam.getBeginnaam());
	}

}
