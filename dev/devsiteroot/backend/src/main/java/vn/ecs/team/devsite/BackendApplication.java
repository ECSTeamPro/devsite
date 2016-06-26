package vn.ecs.team.devsite;

import java.util.ArrayList;
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
//		List<User> lst = dao.findAll();
		List<User> lst = dao.findByProperty("username", "admin");
		if(lst != null)
			System.out.println(lst.get(0).getUsername());
		else
			System.out.println("Null");
		System.out.println(dao.count());
		System.out.println(dao.countByProperty("username", "admin"));
		List<Object[]> ls = new ArrayList<Object[]>();
		ls.add(
			new Object[]{"username","admin"}
		);
		System.out.println(dao.findByProperties(ls, null, null, 0).size());
	}
}
