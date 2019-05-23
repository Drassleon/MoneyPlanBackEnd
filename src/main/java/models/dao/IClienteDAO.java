package models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.entity.Cliente;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Long> {

}
