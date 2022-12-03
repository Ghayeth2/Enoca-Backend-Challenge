package com.challenge.api.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.api.exception.NotFoundException;
import com.challenge.api.model.Employee;
import com.challenge.api.repository.EmployeeRepository;
import com.challenge.api.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
	@Autowired private EmployeeRepository employeeRepo;
	
	@Override
	public Employee savEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Override
	public Employee updatEmployee(Employee employee, int id) {
		Employee employee2 = null;
		if(employeeRepo.existsById(id)) {
			employee2 = employeeRepo.getById(id);
			employee2.setId(id);
			employee2.setName(employee.getName());
			employee2.setDob(employee.getDob());
			employee2.setSalary(employee.getSalary());
			employee2.setCompany(employee.getCompany());
			employee2.setStartingDate(employee.getStartingDate());
		}else {
			try {
				throw new NotFoundException("No such employee with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}
		return employeeRepo.save(employee2);
	}
	
	@Override
	public Integer deleteEmployee(int id) {
		Integer status = 0;
		if(employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
			status = 1;
		}else {
			try {
				throw new NotFoundException("No such employee with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}
		return status;
	}
	
	@Override
	public List<Employee> employees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		if(!(employeeRepo.existsById(id))) {
			try {
				throw new NotFoundException("No such employee with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}else {
			employee = employeeRepo.findById(id).get();
		}
		return employee;
	}
}
