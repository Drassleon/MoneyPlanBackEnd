package pe.edu.upc.moneyplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.moneyplan.models.dao.IClientDAO;
import pe.edu.upc.moneyplan.models.entity.Client;
import pe.edu.upc.moneyplan.service.inter.IClientService;

@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientDAO clientRepository;

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Client t) {
		clientRepository.save(t);
	}

	@Override
	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Client findByEmail(String email) {
		Client client = new Client();
		try {
			client = clientRepository.findByEmail(email);
			if (client != null) {
				return client;
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Boolean validateDuplicate(String email) {
		Boolean clientFound = false;
		Client client;
		try {
			client = clientRepository.findByEmail(email);
			if (client != null) {
				clientFound = true;
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return clientFound;
	}

	@Override
	public Client findByUserId(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.findByUserId(id);
	}
}
