package vn.ecs.team.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ecs.team.model.User;
import vn.ecs.team.service.core.BaseService;

public class UserService extends BaseService<User> {

	
	public User checkLogin(String user, String password){
		Map<String, Object> params = new HashMap<>();
		List<User> lst = this.getDao().buildHQL(params);
		if(lst == null || lst.size() == 0)
			return null;
		return lst.get(0);
	}
	
}
