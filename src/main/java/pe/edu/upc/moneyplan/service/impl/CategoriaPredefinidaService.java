package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ICategoriaPredefinidaDAO;
import pe.edu.upc.moneyplan.models.entity.CategoriaPredefinida;
import pe.edu.upc.moneyplan.service.inter.ICategoriaPredefinidaService;
@Service
public class CategoriaPredefinidaService implements ICategoriaPredefinidaService {
	@Autowired
	private ICategoriaPredefinidaDAO categoriaPredefinidaRepo;
	@Override
	public List<CategoriaPredefinida> findAll() {
		return categoriaPredefinidaRepo.findAll();
	}

	@Override
	public CategoriaPredefinida findById(Long id) {
		return categoriaPredefinidaRepo.findById(id).orElse(null);
	}

	@Override
	public void save(CategoriaPredefinida t) {
		categoriaPredefinidaRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		categoriaPredefinidaRepo.deleteById(id);
	}

}
