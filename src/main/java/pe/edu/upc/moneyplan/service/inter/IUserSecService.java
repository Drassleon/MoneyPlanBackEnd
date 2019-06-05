package pe.edu.upc.moneyplan.service.inter;

import pe.edu.upc.moneyplan.models.entity.UserSec;

public interface IUserSecService extends IService<UserSec>{
	UserSec findByUserName(String username);
}
