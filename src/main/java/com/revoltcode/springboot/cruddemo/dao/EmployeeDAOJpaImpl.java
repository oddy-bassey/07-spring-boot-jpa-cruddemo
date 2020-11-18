package com.revoltcode.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revoltcode.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findall() {
		// create query
		Query theQuery = entityManager.createQuery("from Employee");
		
		// execute query and get result list
		List<Employee> employee = theQuery.getResultList();
		
		//return result
		return employee;
	}

	@Override
	public Employee findById(int theId) {

		// get the employee
		Employee employee = entityManager.find(Employee.class, theId);
		
		if(employee == null) {
			throw new RuntimeException("Employee with id - "+theId+" is not available!");
		}
		// return the employee		
		return employee;
	}

	@Override
	public void save(Employee employee) { 
		
		//save or update the employee
		Employee dbEmployee = entityManager.merge(employee);
		
		//update with id from db...so we can get generated id for save/insert
		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int id) {
		// delete object with primary key
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", id);
		theQuery.executeUpdate();
	}

}
