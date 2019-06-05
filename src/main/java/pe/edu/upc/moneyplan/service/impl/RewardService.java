package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IRecompensaDAO;
import pe.edu.upc.moneyplan.models.entity.Reward;
import pe.edu.upc.moneyplan.service.inter.IRewardService;

@Service
public class RewardService implements IRewardService {
	@Autowired
	private IRecompensaDAO rewardRepo;

	@Override
	public List<Reward> findAll() {
		return rewardRepo.findAll();
	}

	@Override
	public Reward findById(Long id) {
		return rewardRepo.findById(id).orElse(null);
	}

	@Override
	public void save(Reward t) {
		rewardRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		rewardRepo.deleteById(id);
	}

}
