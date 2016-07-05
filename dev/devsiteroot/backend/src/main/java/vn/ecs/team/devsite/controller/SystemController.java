package vn.ecs.team.devsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.ecs.team.devsite.commons.ControllerMapping;
import vn.ecs.team.devsite.dto.UserDto;

@Controller
public class SystemController {

	@RequestMapping(value = "/index.html", name = "index")
	public String index() {
		System.out.println("goto index");
		return ControllerMapping.System.LOGIN;
	}

	@RequestMapping(value = "/login", name="main", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded; charset=utf-8")
	public String login(@ModelAttribute(value="user") UserDto user) {
		System.out.println("go to dashboard");
		System.out.println(user.getUsername() +  " " + user.getPassword());
		return ControllerMapping.System.MAIN;
	}

}
