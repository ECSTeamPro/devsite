package vn.ecs.team.dao;

import vn.ecs.team.hibernate.core.BaseQuery;
import vn.ecs.team.model.Content;

public class ContentDao extends BaseQuery<Integer, Content> {
	
	public ContentDao() {
		super(Content.class);
	}
}
