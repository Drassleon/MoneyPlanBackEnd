package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.SubscriptionPayment;

public interface ISubscriptionPaymentService extends IService<SubscriptionPayment>{
	List<SubscriptionPayment> findByClientId(Long clientId);
}
