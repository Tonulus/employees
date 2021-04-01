package com.noni.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noni.springboot.thymeleafdemo.entity.Employee;
import com.noni.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService empService;
	
	//since we have just 1 constructor, @Autowired is optional
	public EmployeeController(EmployeeService employeeService) {
		empService = employeeService;
	}
	
	//add mapping for /list
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		//get employees from db
		List<Employee> employees = empService.getAllEmployees();
		
		//add employee list to the spring model
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		//create model attribute to bind form data
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	//"/showFormForUpdate" and "employeeId" parameter are the same as in list-employess.html 
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,
									Model model) {
	
		//get the employee from the service
		Employee employee = empService.getEmployee(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		
		//send over to our employee form
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		//save the employee
		empService.saveEmployee(employee);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	//"/delete" and "employeeId" parameter are the same as in list-employess.html
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
	
		//delete the employee
		empService.deleteEmployee(id);
		
		//redirect to /employees/list
		return "redirect:/employees/list";
	}
	
}
