package com.challenge.api.service;

import java.util.List;

import com.challenge.api.model.Employee;


public interface EmployeeService {
	public Employee savEmployee(Employee employee);
	public Employee updatEmployee(Employee employee, int id);
	public Integer deleteEmployee(int id);
	public List<Employee> employees();
	public Employee getEmployeeById(int id);
}
