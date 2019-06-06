package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.SavingGoal;

public interface ISavingGoalService extends IService<SavingGoal>{
	public List<SavingGoal> findByClientId(Long id);
}
