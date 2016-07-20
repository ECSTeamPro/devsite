package vn.ecs.team.dao;


import java.util.List;

import vn.ecs.team.hibernate.core.BaseQuery;
import vn.ecs.team.model.User;

public class UserDao extends BaseQuery<Integer, User> {

	public UserDao() {
		super(User.class);
	}
	
	public User checkLogin(String username, String password){
		String sql = "select * from user where username = '" + username 
				+ "' and password = '" + password + "'";
		List<User> lst = buildSQL(sql, null, User.class);
		if(lst == null || lst.size() == 0)
			return null;
		return lst.get(0);
	}

}
