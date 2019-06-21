package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.DefaultCategory;

@Repository
public interface IDefaultCategoryDAO extends JpaRepository<DefaultCategory, Long> {
	@Query(value="SELECT * FROM default_category c WHERE c.name=?1",nativeQuery=true)
	DefaultCategory findByName(String name);
}
