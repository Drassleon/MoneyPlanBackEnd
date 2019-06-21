package pe.edu.upc.moneyplan.service.inter;

import pe.edu.upc.moneyplan.models.entity.DefaultCategory;

public interface IDefaultCategoryService extends IService<DefaultCategory>{
	public DefaultCategory findByName(String name);

}
