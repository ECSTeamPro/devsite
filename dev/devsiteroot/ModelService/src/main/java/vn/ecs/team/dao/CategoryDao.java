package vn.ecs.team.dao;

import vn.ecs.team.hibernate.core.BaseQuery;
import vn.ecs.team.model.Category;

public class CategoryDao extends BaseQuery<Integer, Category> {
	
	public CategoryDao() {
		super(Category.class);
	}
}
