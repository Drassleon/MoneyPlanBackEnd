package pe.edu.upc.moneyplan.service.inter;

import java.util.List;

import pe.edu.upc.moneyplan.models.entity.CategoriaPersonalizada;

public interface ICategoriaPersonalizadaService extends IService<CategoriaPersonalizada>{
	public List<CategoriaPersonalizada> findByClientId(Long id);
}
