package com.noni.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noni.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//method to sort the list by last name
	//Spring Data JPA MAGIC:
	//Spring Data JPA parse the method name and creates appropriate query behind the scenes
	public List<Employee> findAllByOrderByLastNameAsc();
}
