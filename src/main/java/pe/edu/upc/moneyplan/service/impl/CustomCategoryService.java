package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ICustomCategoryDAO;
import pe.edu.upc.moneyplan.models.entity.CustomCategory;
import pe.edu.upc.moneyplan.service.inter.ICustomCategoryService;

@Service
public class CustomCategoryService implements ICustomCategoryService {
	@Autowired
	private ICustomCategoryDAO customCategoryRepo;

	@Override
	public List<CustomCategory> findAll() {
		return customCategoryRepo.findAll();
	}

	@Override
	public CustomCategory findById(Long id) {
		// TODO Auto-generated method stub
		return customCategoryRepo.findById(id).orElse(null);
	}

	@Override
	public void save(CustomCategory t) {
		customCategoryRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		customCategoryRepo.deleteById(id);
	}

	@Override
	public List<CustomCategory> findByClientId(Long id) {
		// TODO Auto-generated method stub
		return customCategoryRepo.findByClientId(id);
	}

}
