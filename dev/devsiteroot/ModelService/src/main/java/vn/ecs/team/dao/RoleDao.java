package vn.ecs.team.dao;

import vn.ecs.team.hibernate.core.BaseQuery;
import vn.ecs.team.model.Role;

public class RoleDao extends BaseQuery<Integer, Role> {
	
	public RoleDao() {
		super(Role.class);
	}
}
