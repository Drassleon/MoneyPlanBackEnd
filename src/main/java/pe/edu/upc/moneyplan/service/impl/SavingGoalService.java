package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ISavingGoalDAO;
import pe.edu.upc.moneyplan.models.entity.SavingGoal;
import pe.edu.upc.moneyplan.service.inter.ISavingGoalService;

@Service
public class SavingGoalService implements ISavingGoalService {
	@Autowired
	private ISavingGoalDAO savingGoalRepo;

	@Override
	public List<SavingGoal> findAll() {
		return savingGoalRepo.findAll();
	}

	@Override
	public SavingGoal findById(Long id) {
		return savingGoalRepo.findById(id).orElse(null);
	}

	@Override
	public void save(SavingGoal t) {
		savingGoalRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		savingGoalRepo.deleteById(id);
	}

}
