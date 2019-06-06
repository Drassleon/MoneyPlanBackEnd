package pe.edu.upc.moneyplan.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.moneyplan.models.entity.SavingGoal;

@Repository
public interface ISavingGoalDAO extends JpaRepository<SavingGoal, Long> {
	@Query(value = "SELECT * FROM saving_goal c WHERE c.client_id=?1", nativeQuery = true)
	List<SavingGoal> findByClientId(Long id);
}
