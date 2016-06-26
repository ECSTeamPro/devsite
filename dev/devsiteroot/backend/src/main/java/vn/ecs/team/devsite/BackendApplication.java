package vn.ecs.team.devsite;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.ecs.team.dao.UserDao;
import vn.ecs.team.model.User;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
		UserDao dao = new UserDao();
		List<User> lst = dao.findAll();
		if(lst != null)
			System.out.println(lst.size());
		else
			System.out.println("Null");
	}
}
