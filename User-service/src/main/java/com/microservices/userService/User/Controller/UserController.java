package com.microservices.userService.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userService.User.Service.UserService;
import com.microservices.userService.User.VO.ResponseTemplateVo;
import com.microservices.userService.User.entity.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		
		return userService.save(user);	
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVo getUserWithDepartment(@PathVariable("id") Long userId) {
		
		return userService.getUserWithDepartment(userId);
	}
}
