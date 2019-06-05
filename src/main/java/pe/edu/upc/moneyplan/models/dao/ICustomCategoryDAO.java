package pe.edu.upc.moneyplan.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.CustomCategory;

@Repository
public interface ICustomCategoryDAO extends JpaRepository<CustomCategory, Long> {
	@Query(value = "SELECT * FROM CustomCategory c WHERE c.Client_Id=?1", nativeQuery = true)
	List<CustomCategory> findByClientId(Long id);
}
