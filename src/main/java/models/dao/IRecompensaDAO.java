package models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.entity.Recompensa;

@Repository
public interface IRecompensaDAO extends JpaRepository<Recompensa, Long>{

}
