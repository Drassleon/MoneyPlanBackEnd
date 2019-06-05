package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.Client;

@Repository
public interface IClientDAO extends JpaRepository<Client, Long> {
	@Query(value = "SELECT * FROM Client c WHERE c.Email=?1", nativeQuery = true)
	Client findByEmail(String email);
}
