package gp.interfac;

import java.util.List;


public interface IDao {
	
	
	public <T> T find(Class<T> clazz, Object id);
	
	public <T> List<T> findAll(String query, Class<T> clazz);
	
	public <T> T add(T entity);
	
	public <T> T update(T entity);
	
	public <T> void remove(Class<T> clazz, Object pk);
	
}
