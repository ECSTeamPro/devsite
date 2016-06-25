package vn.ecs.team.hibernate.core;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseQuery<KEY, T> implements QueryInf<KEY, T> {

	Logger logger = LoggerFactory.getLogger(BaseQuery.class);
	TransactionUtils<KEY, T> tranx;
	Class<T> entityClass;
	
	public BaseQuery(){
		
	}
	
	public BaseQuery(Class<T> entityClass){
		this.entityClass = entityClass;
		tranx = new TransactionUtils<>(this);
	}
	
	Class<T> getEntity(){
		return this.entityClass;
	}
	
	@Override
	public void save(T t) {
		
		try{
			tranx.startTransaction();
			getSession().save(t);
			tranx.commit();	
			
		}catch(Exception e){
			logger.error("save error", e);
			tranx.rollback();
		}
		
		
		
	}

	@Override
	public void saveOrUpdate(T t) {
		try{
			tranx.startTransaction();
			getSession().saveOrUpdate(t);
			tranx.commit();	
			
		}catch(Exception e){
			logger.error("save or update error", e);
			tranx.rollback();
		}
	}

	@Override
	public void delete(T t) {
		try{
			tranx.startTransaction();
			getSession().delete(t);
			tranx.commit();	
			
		}catch(Exception e){
			logger.error("delete error", e);
			tranx.rollback();
		}
	}

	@Override
	public T findById(KEY id) {
		try{
			tranx.startTransaction();
			T t = getSession().find(this.entityClass, (Serializable)id);
			tranx.commit();	
			return t;
		}catch(Exception e){
			logger.error("findById error", e);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public List<T> findByProperty(String property, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		try{
			tranx.startTransaction();
			Query query = getSession().createQuery("from " + this.entityClass.getName());
			List<T> lst = query.list();
			tranx.commit();	
			return lst;
		}catch(Exception e){
			logger.error("find all ", e);
			tranx.rollback();
		}
		return null;
	}

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	@Override
	public void update(T t) {
		try{
			tranx.startTransaction();
			getSession().merge(t);
			tranx.commit();	
			
		}catch(Exception e){
			log("update error", e.getMessage(), 0);
			tranx.rollback();
		}
	}
	
	private void log(String normalMess, String errorMess, int type){
		String s  = String.format("[%s] [%s]  [%s]", this.entityClass.getName(), normalMess, errorMess).toString();
		if(type == 0)
			logger.error(s);
		else if(type == 1) logger.debug(s);
		else logger.info(s);
		
	}
}
