package com.challenge.api.service;

import java.util.List;

import com.challenge.api.model.Company;
import com.challenge.api.model.Employee;


public interface CompanyService {
	public Company saveCompany(Company company);
	public Company updateCompany(Company company, int id);
	public Integer deleteCompany(int id);
	public List<Company> companies();
	public List<Employee> companyEmployees(int id);
	public Company getCompanyById(int id);
}
