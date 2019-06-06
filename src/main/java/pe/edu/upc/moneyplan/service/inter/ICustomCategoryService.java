package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.CustomCategory;

public interface ICustomCategoryService extends IService<CustomCategory>{
	public List<CustomCategory> findByClientId(Long id);
	public CustomCategory findByName(String name,Long id);
	public Boolean isDuplicate(String name,Long id);
}
