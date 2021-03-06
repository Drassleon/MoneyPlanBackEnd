package pe.edu.upc.moneyplan.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pe.edu.upc.moneyplan.models.dao.IUserSecDAO;
import pe.edu.upc.moneyplan.models.entity.UserSec;
import pe.edu.upc.moneyplan.service.inter.IUserSecService;

@Service
public class UserSecService implements IUserSecService {

	@Autowired
	private IUserSecDAO userSecRepository;

	@Override
	public List<UserSec> findAll() {
		return userSecRepository.findAll();
	}

	@Override
	public UserSec findById(Long id) {
		return userSecRepository.findById(id).orElse(null);
	}

	@Override
	public void save(UserSec t) {
		userSecRepository.save(t);
	}

	@Override
	public void deleteById(Long id) {
		userSecRepository.deleteById(id);
	}

	@Override
	public UserSec findByUserName(String username) {
		byte[] decodedBytes = Base64.getDecoder().decode(username);
		String decodedString = new String(decodedBytes);
		List<UserSec> aux = userSecRepository.findByUserName(decodedString);
		if(aux.size()==0)
		{
			return null;
		}
		return aux.get(0);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserSec user = userSecRepository.findByUserName(username).get(0);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

		return new User(user.getUsername(), user.getPassword(), authorities);
	}
}
