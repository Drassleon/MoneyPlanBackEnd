package models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.entity.MetaAhorro;

@Repository
public interface IMetaAhorroDAO extends JpaRepository<MetaAhorro, Long>{

}