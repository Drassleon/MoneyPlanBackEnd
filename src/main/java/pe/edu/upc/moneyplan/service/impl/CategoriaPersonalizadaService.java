package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ICategoriaPersonalizadaDAO;
import pe.edu.upc.moneyplan.models.entity.CategoriaPersonalizada;
import pe.edu.upc.moneyplan.service.inter.ICategoriaPersonalizadaService;
@Service
public class CategoriaPersonalizadaService implements ICategoriaPersonalizadaService {
	@Autowired
	private ICategoriaPersonalizadaDAO categoriaPersonalizadaRepo;
	@Override
	public List<CategoriaPersonalizada> findAll() {
		return categoriaPersonalizadaRepo.findAll();
	}

	@Override
	public CategoriaPersonalizada findById(Long id) {
		// TODO Auto-generated method stub
		return categoriaPersonalizadaRepo.findById(id).orElse(null);
	}

	@Override
	public void save(CategoriaPersonalizada t) {
		categoriaPersonalizadaRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		categoriaPersonalizadaRepo.deleteById(id);
	}

}
