package vn.ecs.team.devsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.ecs.team.dao.UserDao;
import vn.ecs.team.dto.UserDto;
import vn.ecs.team.hibernate.core.QueryInf;
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
		
		//test function, wait for gralde load build
		
		StringBuilder sb = new StringBuilder("select username as userName, password as password, phone * 3 as age from User");
		Map<String, Object> scalar = new HashMap<>();
		scalar.put("userName", QueryInf.TYPE_STRING);
		scalar.put("password", QueryInf.TYPE_STRING);
		scalar.put("age", QueryInf.TYPE_INTEGER);
		
		System.out.println("test function build sql with scalar");
		List<UserDto> lstDto = dao.buildSQL(sb.toString(), scalar, UserDto.class);
		if(lstDto == null || lstDto.size() == 0)
			System.out.println("null");
		else{
			UserDto dto = lstDto.get(0);
			System.out.println(dto.toString());
		}
		
		//
		System.out.println("test function build sql not with scalar");
		List<User> lstUser = dao.buildSQL("select * from User", null, User.class);
		if(lstUser == null || lstUser.size() == 0)
			System.out.println("null");
		else{
			User dto = lstUser.get(0);
			System.out.println(dto.getUsername() + " " + dto.getPassword() + " " + dto.getPhone());
		}
		
		System.out.println("HQL");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "admin");
		List<User> lu = dao.buildHQL(params);
		if(lu == null || lu.size() == 0)
			System.out.println("null");
		else{
			User dto = lu.get(0);
			System.out.println(dto.getUsername() + " " + dto.getPassword() + " " + dto.getPhone());
		}

		// 
	}
}
