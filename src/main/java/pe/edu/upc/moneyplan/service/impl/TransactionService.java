package pe.edu.upc.moneyplan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ITransactionDAO;
import pe.edu.upc.moneyplan.models.entity.Transaction;
import pe.edu.upc.moneyplan.service.inter.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionDAO transactionRepo;

	@Override
	public List<Transaction> findAll() {
		return transactionRepo.findAll();
	}

	@Override
	public Transaction findById(Long id) {
		return transactionRepo.findById(id).orElse(null);
	}

	@Override
	public void save(Transaction t) {
		transactionRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		transactionRepo.deleteById(id);
	}

	@Override
	public List<Transaction> findByClientId(Long id) {
		// TODO Auto-generated method stub
		List<Transaction> transactions = new ArrayList<>();
		try {
			transactions = transactionRepo.findByClientId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
