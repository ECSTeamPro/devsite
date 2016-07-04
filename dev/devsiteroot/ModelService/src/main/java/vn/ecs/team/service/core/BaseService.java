package vn.ecs.team.service.core;

import vn.ecs.team.hibernate.core.BaseQuery;

public class BaseService<T> implements ServiceInf<T> {

	private BaseQuery<Integer, T> dao;

	public BaseService() {
		dao = new BaseQuery<Integer, T>();
	}
	
	public BaseQuery getDao(){
		return dao;
	}
}
