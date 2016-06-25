package vn.ecs.team.hibernate.core;

import java.util.List;

public interface QueryInf<KEY, T> {
	
	public void save(T t);
	
	public void update(T t);
	
	public void saveOrUpdate(T t);
	
	public void delete(T t);
	
	public T findById(KEY id);
	
	public List<T> findByProperty(String property, Object value);
	
	public List<T> findAll();
	
}
