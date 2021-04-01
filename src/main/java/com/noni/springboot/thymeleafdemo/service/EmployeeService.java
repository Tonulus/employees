package com.noni.springboot.thymeleafdemo.service;

import java.util.List;

import com.noni.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(int employeeId);
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(int employeeId);	
}
