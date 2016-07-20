package vn.ecs.team.devsite.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ecs.team.devsite.commons.ControllerMapping;
import vn.ecs.team.devsite.dto.UserDto;
import vn.ecs.team.model.User;
import vn.ecs.team.service.UserService;

@Controller
public class SystemController {
	private UserService userService;
	

	public SystemController() {
		userService = new UserService();
	}

	@RequestMapping(value = "/index.html", name = "index")
	public String index() {
		System.out.println("goto index");
		return ControllerMapping.System.LOGIN;
	}

	@RequestMapping(value = "/dashboard")
	public String dashboard(HttpServletRequest request) {
		System.out.println("go to dashboard");
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user.toString());
		return ControllerMapping.System.MAIN;
	} 

	@RequestMapping(value = "/login", name = "main", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded; charset=utf-8")
	public String login(@ModelAttribute(value = "user") UserDto user, HttpServletRequest request) {
		User u = userService.checkLogin(user.getUsername(), user.getPassword());
		if (u == null)
			return "redirect:/index.html";
		request.getSession().setAttribute("user", u);
		return "redirect:/dashboard";
	}
}
