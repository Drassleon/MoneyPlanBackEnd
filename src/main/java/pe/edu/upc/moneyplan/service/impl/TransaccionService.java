package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.ITransaccionDAO;
import pe.edu.upc.moneyplan.models.entity.Transaccion;
import pe.edu.upc.moneyplan.service.inter.ITransaccionService;
@Service
public class TransaccionService implements ITransaccionService {
	
	@Autowired
	private ITransaccionDAO transaccionRepo;
	@Override
	public List<Transaccion> findAll() {
		return transaccionRepo.findAll();
	}

	@Override
	public Transaccion findById(Long id) {
		return transaccionRepo.findById(id).orElse(null);
	}

	@Override
	public void save(Transaccion t) {
		transaccionRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		transaccionRepo.deleteById(id);
	}

}
