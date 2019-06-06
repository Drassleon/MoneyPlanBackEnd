package pe.edu.upc.moneyplan.service.inter;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.upc.moneyplan.models.entity.UserSec;

public interface IUserSecService extends IService<UserSec>,UserDetailsService{
	public UserSec findByUserName(String username);
}
