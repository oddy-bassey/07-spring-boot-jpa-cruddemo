package com.revoltcode.springboot.cruddemo.dao;

import java.util.List;

import com.revoltcode.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findall();
	
	public Employee findById(int theId);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
}
