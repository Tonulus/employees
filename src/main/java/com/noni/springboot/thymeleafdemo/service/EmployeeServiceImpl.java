package com.noni.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noni.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.noni.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRep;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		employeeRep = employeeRepository;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
 
		return employeeRep.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee getEmployee(int employeeId) {

		Optional<Employee> result = employeeRep.findById(employeeId);
		
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		}
		else {
			//we didn't find the employee with employeeId
			throw new RuntimeException("Did not find employee id: " + employeeId);
		}
		
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {

		employeeRep.save(employee);
	}

	@Override
	public void deleteEmployee(int employeeId) {

		employeeRep.deleteById(employeeId);
	}

}
