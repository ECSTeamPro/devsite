package vn.ecs.team.service;

import vn.ecs.team.dao.UserDao;
import vn.ecs.team.model.User;
import vn.ecs.team.service.core.ServiceInf;

public class UserService implements ServiceInf<User> {

	private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}
	
	public User checkLogin(String username, String password){
		return dao.checkLogin(username, password);
	}
	
}
