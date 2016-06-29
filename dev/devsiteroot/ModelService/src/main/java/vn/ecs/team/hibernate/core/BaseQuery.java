package vn.ecs.team.hibernate.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseQuery<KEY, T> implements QueryInf<KEY, T> {

	Logger logger = LoggerFactory.getLogger(BaseQuery.class);
	TransactionUtils<KEY, T> tranx;
	Class<T> entityClass;

	public BaseQuery() {

	}

	public BaseQuery(Class<T> entityClass) {
		this.entityClass = entityClass;
		tranx = new TransactionUtils<>(this);
	}

	Class<T> getEntity() {
		return this.entityClass;
	}

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	private void log(String normalMess, String errorMess, int type) {
		String s = String.format("[%s] [%s]  [%s]", this.entityClass.getName(), normalMess, errorMess).toString();
		if (type == 0)
			logger.error(s);
		else if (type == 1)
			logger.debug(s);
		else
			logger.info(s);

	}

	@Override
	public void save(T t) {

		try {
			tranx.startTransaction();
			getSession().save(t);
			tranx.commit();

		} catch (Exception e) {
			logger.error("save error", e);
			tranx.rollback();
		}

	}

	@Override
	public void saveOrUpdate(T t) {
		try {
			tranx.startTransaction();
			getSession().saveOrUpdate(t);
			tranx.commit();

		} catch (Exception e) {
			logger.error("save or update error", e);
			tranx.rollback();
		}
	}

	@Override
	public void delete(T t) {
		try {
			tranx.startTransaction();
			getSession().delete(t);
			tranx.commit();

		} catch (Exception e) {
			logger.error("delete error", e);
			tranx.rollback();
		}
	}

	@Override
	public T findById(KEY id) {
		try {
			tranx.startTransaction();
			T t = (T) getSession().get(this.entityClass, (Serializable) id);
			tranx.commit();

			return t;
		} catch (Exception e) {
			logger.error("findById error", e);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public List<T> findByProperty(String property, Object value) {
		try {
			tranx.startTransaction();
			Query query = getSession()
					.createQuery("from " + this.entityClass.getName() + " m where m." + property + " = :value");
			query.setParameter("value", value);
			List<T> lst = query.list();
			tranx.commit();
			return lst;
		} catch (Exception e) {
			logger.error("find by property " + property + "=" + value, e);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public List<T> findAll() {
		try {
			tranx.startTransaction();
			Query query = getSession().createQuery("from " + this.entityClass.getName());
			List<T> lst = query.list();
			tranx.commit();
			return lst;
		} catch (Exception e) {
			logger.error("find all ", e);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public void update(T t) {
		try {
			tranx.startTransaction();
			getSession().merge(t);
			tranx.commit();

		} catch (Exception e) {
			log("update error", e.getMessage(), 0);
			tranx.rollback();
		}
	}

	@Override
	public List<T> findByProperties(List<Object[]> properties, String[] groupBys, String[] orderBys, int limit) {
		StringBuilder sb = new StringBuilder("from " + this.getEntity().getName() + " m");
		if (properties != null && properties.size() > 0) {
			sb.append(" where ");
			for (int i = 0; i < properties.size(); i++) {
				Object[] obj = properties.get(i);
				sb.append("m." + obj[0] + " = :" + obj[0]);
				if (i < properties.size() - 1)
					sb.append(" and ");
			}
		}
		if (groupBys != null && groupBys.length > 0) {
			sb.append(" group by ");
			for (int i = 0; i < groupBys.length; i++) {
				sb.append(groupBys[i]);
				if (i < groupBys.length - 1)
					sb.append(", ");
			}
		}

		if (orderBys != null && orderBys.length > 0) {
			sb.append(" order by ");
			for (int i = 0; i < orderBys.length; i++) {
				sb.append(orderBys[i]);
				if (i < orderBys.length - 1)
					sb.append(", ");
			}
		}

		if (limit > 0)
			sb.append(" limit " + limit);
		try {
			tranx.startTransaction();
			Query query = getSession().createQuery(sb.toString());
			if (properties != null && properties.size() > 0)
				for (int i = 0; i < properties.size(); i++) {
					Object[] obj = properties.get(i);
					query.setParameter((String) obj[0], obj[1]);
				}
			List<T> lst = query.list();
			tranx.commit();
			return lst;
		} catch (Exception e) {
			log("find by properties error", e.getMessage(), 0);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public Long count() {
		try {
			tranx.startTransaction();
			Query query = getSession().createQuery(" select count(*) from " + this.getEntity().getName());
			Long count = (Long) query.uniqueResult();
			tranx.commit();
			return count;
		} catch (Exception e) {
			log("find by properties error", e.getMessage(), 0);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public Long countByProperty(String property, Object value) {
		try {
			tranx.startTransaction();
			Query query = getSession().createQuery(
					" select count(*) from " + this.getEntity().getName() + " m where m." + property + "=:value");
			query.setParameter("value", value);
			Long count = (Long) query.uniqueResult();
			tranx.commit();
			return count;
		} catch (Exception e) {
			log("find by properties error", e.getMessage(), 0);
			tranx.rollback();
		}

		return null;
	}

	@Override
	public Long countByProperties(List<Object[]> properties) {
		StringBuilder sb = new StringBuilder("select count(*) from " + this.getEntity().getName() + " m");
		if (properties != null && properties.size() > 0) {
			sb.append(" where ");
			for (int i = 0; i < properties.size(); i++) {
				sb.append("m." + properties.get(i)[0] + " = :" + properties.get(i)[0]);
				if (i < properties.size() - 1) {
					sb.append(" and ");
				}
			}
		}

		try {
			tranx.startTransaction();
			Query query = getSession().createQuery(sb.toString());
			if (properties != null && properties.size() > 0) {
				
				for (int i = 0; i < properties.size(); i++)
					query.setParameter((String)properties.get(i)[0], properties.get(i)[1]);
			}

			Long count = (Long) query.uniqueResult();
			tranx.commit();
			return count;
		} catch (Exception e) {
			log("find by properties error", e.getMessage(), 0);
			tranx.rollback();
		}
		return null;
	}

	@Override
	public <K> List<K> buildSQL(String sql, Map<String, Object> scalar, Class dto) {
		if(sql == null || sql.isEmpty()){
			log("sql cannot be null", null, 0);
			//can throw exception here
			return null;
		}
		try{
			tranx.startTransaction();
			SQLQuery query = getSession().createSQLQuery(sql);
			if(scalar != null && !scalar.isEmpty()){
				//add scalar for dto
				Set<String> keys = scalar.keySet();
				for(String key: keys){
					Object value = scalar.get(key);
					buildScalarForSqlQuery(query, key, value);
				}
				query.setResultTransformer(Transformers.aliasToBean(dto));
			}
			if(scalar == null)
				query.addEntity(dto);
			
			List lst = query.list();
			tranx.commit();
			return lst;
			
		}catch(Exception e){
			//e.printStackTrace();
			tranx.rollback();
			log("build sql error", e.getMessage(), 0);
		}
		//log("Method: buildSQL not support", null, 0);
		return null;
	}

	private void buildScalarForSqlQuery(SQLQuery query, String key, Object value){
		//check instance of value to add exactly scalar with dto 
		if(value instanceof StringType){
			query.addScalar(key, TYPE_STRING);
			return;
		}
		if(value instanceof DoubleType){
			query.addScalar(key, TYPE_DOUBLE);
			return;
		}
		if(value instanceof IntegerType){
			query.addScalar(key, TYPE_INTEGER);
			return;
		}
		if(value instanceof DateType){
			query.addScalar(key, TYPE_DATE);
			return;
		}
	}
	
	@Override
	public List<T> buildHQL(Map<String, Object> params) {

		StringBuilder sb = new StringBuilder(" from " + getEntity().getName() + " u ");
		if(params != null && params.size() != 0){
			sb.append(" where ");
			Set<String> keys = params.keySet();
			int i = 0;
			for(String key : keys){
				sb.append("u."+key+"= :"+key);
				i++;
				if(i < keys.size() - 1) sb.append(" and ");
			}
		}
		try{
			tranx.startTransaction();
			Query query = getSession().createQuery(sb.toString());
			if(params != null && params.size() != 0){
				Set<String> keys = params.keySet();
				for(String key : keys){
					query.setParameter(key, params.get(key));
				}
			}

			List lst = query.list();
			tranx.commit();
			return lst;
		}catch(Exception e){
			tranx.rollback();
			log("buildHQL error", e.getMessage(), 0);
		}
		//log("Method: buildHQL not support", null, 0);
		return null;
	}

}
