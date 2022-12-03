package com.challenge.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
