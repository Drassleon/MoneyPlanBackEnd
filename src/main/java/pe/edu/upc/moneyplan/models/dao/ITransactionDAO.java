package pe.edu.upc.moneyplan.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.Transaction;

@Repository
public interface ITransactionDAO extends JpaRepository<Transaction, Long> {
	@Query(value = "SELECT * FROM transaction t WHERE t.client_id=?1", nativeQuery = true)
	List<Transaction> findByClientId(Long id);
}
