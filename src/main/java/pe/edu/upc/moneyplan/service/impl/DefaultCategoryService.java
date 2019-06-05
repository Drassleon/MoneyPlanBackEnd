package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IDefaultCategoryDAO;
import pe.edu.upc.moneyplan.models.entity.DefaultCategory;
import pe.edu.upc.moneyplan.service.inter.IDefaultCategoryService;

@Service
public class DefaultCategoryService implements IDefaultCategoryService {
	@Autowired
	private IDefaultCategoryDAO defaultCategoryRepo;

	@Override
	public List<DefaultCategory> findAll() {
		return defaultCategoryRepo.findAll();
	}

	@Override
	public DefaultCategory findById(Long id) {
		return defaultCategoryRepo.findById(id).orElse(null);
	}

	@Override
	public void save(DefaultCategory t) {
		defaultCategoryRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		defaultCategoryRepo.deleteById(id);
	}

}
