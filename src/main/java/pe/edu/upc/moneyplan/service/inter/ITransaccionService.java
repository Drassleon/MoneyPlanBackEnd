package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.Transaccion;

public interface ITransaccionService extends IService<Transaccion>{
	public List<Transaccion> findByClientId(Long id);
}
