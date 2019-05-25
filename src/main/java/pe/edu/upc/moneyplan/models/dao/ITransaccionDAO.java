package models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.entity.Transaccion;

@Repository
public interface ITransaccionDAO extends JpaRepository<Transaccion, Long>{

}
