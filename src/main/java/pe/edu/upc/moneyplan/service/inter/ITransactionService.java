package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.Transaction;

public interface ITransactionService extends IService<Transaction>{
	public List<Transaction> findByClientId(Long id);
}
