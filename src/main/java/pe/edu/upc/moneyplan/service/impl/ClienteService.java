package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IClienteDAO;
import pe.edu.upc.moneyplan.models.entity.Cliente;
import pe.edu.upc.moneyplan.service.inter.IClienteService;
@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteDAO clienteRepository;
	
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Cliente t) {
		clienteRepository.save(t);
	}

	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

}
