package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IRecompensaDAO;
import pe.edu.upc.moneyplan.models.entity.Recompensa;
import pe.edu.upc.moneyplan.service.inter.IRecompensaService;
@Service
public class RecompensaService implements IRecompensaService {
@Autowired
private IRecompensaDAO recompensaRepo;
	@Override
	public List<Recompensa> findAll() {
		return recompensaRepo.findAll();
	}

	@Override
	public Recompensa findById(Long id) {
		return recompensaRepo.findById(id).orElse(null);
	}

	@Override
	public void save(Recompensa t) {
		recompensaRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		recompensaRepo.deleteById(id);
	}

}
