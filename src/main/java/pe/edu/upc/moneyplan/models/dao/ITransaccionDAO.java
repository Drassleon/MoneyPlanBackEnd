package pe.edu.upc.moneyplan.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.Transaccion;

@Repository
public interface ITransaccionDAO extends JpaRepository<Transaccion, Long>{
	@Query(
			  value = "SELECT * FROM transaccion t WHERE t.cliente_id=?1", 
			  nativeQuery = true)
			List<Transaccion> findByClientId(Long id);
}
