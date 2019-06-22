package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ISubscriptionPaymentDAO;
import pe.edu.upc.moneyplan.models.entity.SubscriptionPayment;
import pe.edu.upc.moneyplan.service.inter.ISubscriptionPaymentService;
@Service
public class SubscriptionPaymentService implements ISubscriptionPaymentService {

	@Autowired
	private ISubscriptionPaymentDAO subscriptionRepository;
	
	@Override
	public List<SubscriptionPayment> findAll() {
		// TODO Auto-generated method stub
		return subscriptionRepository.findAll();
	}

	@Override
	public SubscriptionPayment findById(Long id) {
		// TODO Auto-generated method stub
		return subscriptionRepository.findById(id).orElse(null);
	}

	@Override
	public void save(SubscriptionPayment t) {
		// TODO Auto-generated method stub
		subscriptionRepository.save(t);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		subscriptionRepository.deleteById(id);
	}

	@Override
	public List<SubscriptionPayment> findByClientId(Long clientId) {
		// TODO Auto-generated method stub
		return subscriptionRepository.findByClientId(clientId);
	}

}
