package vn.ecs.team.dao;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import vn.ecs.team.hibernate.core.BaseQuery;

public class UserDao extends BaseQuery<Integer, User> {

	public UserDao() {
		super(User.class);
	}

}
