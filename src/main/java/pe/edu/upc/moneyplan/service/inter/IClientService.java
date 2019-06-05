package pe.edu.upc.moneyplan.service.inter;

import pe.edu.upc.moneyplan.models.entity.Client;

public interface IClientService extends IService<Client>  {
	public Client findByEmail(String email);
	public Boolean validateDuplicate(String email);
}
