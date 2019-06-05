package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.UserSec;

@Repository
public interface IUserSecDAO extends JpaRepository<UserSec, Long> {
	@Query(value = "SELECT * FROM security s WHERE s.user_name=?1", nativeQuery = true)
	UserSec findByUserName(String username);
}
