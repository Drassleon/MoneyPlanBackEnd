package pe.edu.upc.moneyplan.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.moneyplan.models.entity.CategoriaPredefinida;

@Repository
public interface ICategoriaPredefinidaDAO extends JpaRepository<CategoriaPredefinida, Long>{

}
