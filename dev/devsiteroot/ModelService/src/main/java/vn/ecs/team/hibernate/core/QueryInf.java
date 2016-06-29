package vn.ecs.team.hibernate.core;

import java.util.List;
import java.util.Map;

import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

public interface QueryInf<KEY, T> {
	
	public static DoubleType TYPE_DOUBLE = DoubleType.INSTANCE;
	public static IntegerType TYPE_INTEGER = IntegerType.INSTANCE;
	public static LongType TYPE_LONG = LongType.INSTANCE;
	public static StringType TYPE_STRING = StringType.INSTANCE;
	public static DateType TYPE_DATE = DateType.INSTANCE;
	
	
	public void save(T t);
	
	public void update(T t);
	
	public void saveOrUpdate(T t);
	
	public void delete(T t);
	
	public T findById(KEY id);
	
	public List<T> findByProperty(String property, Object value);
	
	public List<T> findByProperties(List<Object[]> properties, String[] groupBys, String[] orderBys, int limit);
	
	public List<T> findAll();
	
	public Long count();
	
	public Long countByProperty(String property, Object value);
	
	public Long countByProperties(List<Object[]> properties);
	
	public <K> List<K> buildSQL(String sql, Map<String, Object> scalar, Class dto);
	
	public List<T> buildHQL(Map<String, Object> params);
}
