package vn.ecs.team.devsite;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.ecs.team.dao.UserDao;
import vn.ecs.team.devsite.config.RequestFilter;
import vn.ecs.team.model.User;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
		UserDao dao = new UserDao();
		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("username", "kyph");
//		params.put("password", "123456");
		List<User> lst = dao.buildSQL("select * from user", null, User.class);
		if(lst != null)
		System.out.println(lst.size());
	}
	
	@Bean
	RequestFilter requestFilter(){
		return new RequestFilter();
	}
}
