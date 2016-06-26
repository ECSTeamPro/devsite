package vn.ecs.team.dao;


import vn.ecs.team.hibernate.core.BaseQuery;
import vn.ecs.team.model.User;

public class UserDao extends BaseQuery<Integer, User> {

	public UserDao() {
		super(User.class);
	}

}
