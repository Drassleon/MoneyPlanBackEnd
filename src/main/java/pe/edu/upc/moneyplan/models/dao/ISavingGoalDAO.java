package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.SavingGoal;

@Repository
public interface ISavingGoalDAO extends JpaRepository<SavingGoal, Long> {

}
