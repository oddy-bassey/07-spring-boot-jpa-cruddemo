package com.revoltcode.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.revoltcode.springboot.cruddemo.entity.Employee;
import com.revoltcode.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	//inject the employee dao (via constructor injection)
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//expose "/employess" and return list of employess
	@GetMapping("/employees")
	public List<Employee> findallEmployees(){
		return employeeService.findAll();
	}
	
	//Reading a single employee
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee not found - "+employeeId);
		}
		
		return employee;
	}
	
	//saving employee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		
		employeeService.save(employee);
		
		return employee;
	}
	
	//updating an existing employee
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		
		return employee;
	}
	
	//deleting an exiting employee
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee with id - "+employeeId+" not found!");
		}
		employeeService.delete(employeeId);
		
		return "Deleted employee with id - "+employeeId;
	}
}























