package com.microservices.userService.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.userService.User.Repository.UserRepository;
import com.microservices.userService.User.VO.Department;
import com.microservices.userService.User.VO.ResponseTemplateVo;
import com.microservices.userService.User.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public ResponseTemplateVo getUserWithDepartment(Long userId) {
		// TODO Auto-generated method stub
		ResponseTemplateVo vo= new ResponseTemplateVo();
		User user= userRepository.findByUserId(userId);
		
		Department department = restTemplate.getForObject("http://localhost:9001/department/"+user.getDepartmentId(), Department.class);
		
		vo.setUser(user);
		vo.setDepartment(department);
		
		return vo;
	}
}
