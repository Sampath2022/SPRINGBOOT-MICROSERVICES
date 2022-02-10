package com.microservices.example.departmentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.example.departmentservice.entity.Department;
import com.microservices.example.departmentservice.repository.DepartmentRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

	 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentRepository departmrntRepository;

	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		
		log.info("inside the departmentRepository save method");
		return departmrntRepository.save(department);
	}

	public Department findDepartmentById(Long departmentId) {
		// TODO Auto-generated method stub
		log.info("inside the departmentRepository findDepartmentById method");
		return departmrntRepository.findByDepartmentId(departmentId);
	}

	public List<Department> saveAll(List<Department> listOfDepartment) {
		// TODO Auto-generated method stub
		return departmrntRepository.saveAll(listOfDepartment);
	}

	public Object findById(Long departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
