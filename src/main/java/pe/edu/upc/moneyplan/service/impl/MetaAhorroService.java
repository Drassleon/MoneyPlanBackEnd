package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IMetaAhorroDAO;
import pe.edu.upc.moneyplan.models.entity.MetaAhorro;
import pe.edu.upc.moneyplan.service.inter.IMetaAhorroService;
@Service
public class MetaAhorroService implements IMetaAhorroService {
	@Autowired
	private IMetaAhorroDAO metaAhorroRepo;
	@Override
	public List<MetaAhorro> findAll() {
		return metaAhorroRepo.findAll();
	}

	@Override
	public MetaAhorro findById(Long id) {
		return metaAhorroRepo.findById(id).orElse(null);
	}

	@Override
	public void save(MetaAhorro t) {
		metaAhorroRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		metaAhorroRepo.deleteById(id);
	}

}
