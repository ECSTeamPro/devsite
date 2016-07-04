package vn.ecs.team.devsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.ecs.team.devsite.commons.ControllerMapping;

@Controller
public class SystemController {
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="username")String username, @RequestParam(value="password")String password){
		return ControllerMapping.System.LOGIN;
	}
	
}
