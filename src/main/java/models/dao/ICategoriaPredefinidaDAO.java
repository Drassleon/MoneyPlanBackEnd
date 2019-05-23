package models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.entity.CategoriaPredefinida;

@Repository
public interface ICategoriaPredefinidaDAO extends JpaRepository<CategoriaPredefinida, Long>{

}
