package service.inter;

import java.util.List;

public interface IService<T> {
	
	public List<T> findAll();
	
	public T findById(Long id);
	
	public void save(T t);
	
	public void deleteById(Long id);
}
