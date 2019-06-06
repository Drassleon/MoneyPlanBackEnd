package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.Client;

@Repository
public interface IClientDAO extends JpaRepository<Client, Long> {
	@Query(value = "SELECT * FROM client c WHERE c.email=?1", nativeQuery = true)
	Client findByEmail(String email);
}
