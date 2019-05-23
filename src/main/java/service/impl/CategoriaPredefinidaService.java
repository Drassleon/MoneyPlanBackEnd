package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.dao.ICategoriaPredefinidaDAO;
import models.entity.CategoriaPredefinida;
import service.inter.ICategoriaPredefinidaService;
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
