package com.challenge.api.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.api.exception.NotFoundException;
import com.challenge.api.model.Company;
import com.challenge.api.model.Employee;
import com.challenge.api.repository.CompanyRepository;
import com.challenge.api.service.CompanyService;
import com.challenge.api.service.EmployeeService;
@Service
public class CompanyServiceImp implements CompanyService {
	
	@Autowired private CompanyRepository companyRepo;
	@Autowired private EmployeeService employeeServ;
	
	@Override
	public Company saveCompany(Company company) {
		return companyRepo.save(company);
	}

	@Override
	public Company updateCompany(Company company, int id) {
		Company company2 = null;
		if(companyRepo.existsById(id)) {
			company2 = companyRepo.findById(id).get();
			company2.setId(id);
			company2.setName(company.getName());
		}else {
			try {
				throw new NotFoundException("No such company with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}
		return companyRepo.save(company2);	
	}

	@Override
	public Integer deleteCompany(int id) {
		Integer status = 0;
		if(companyRepo.existsById(id)) {
			status = 1;
			companyRepo.deleteById(id);
		}else {
			try {
				throw new NotFoundException("No such company with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}
		return status;
	}

	@Override
	public List<Company> companies() {
		return companyRepo.findAll();
	}

	@Override
	public List<Employee> companyEmployees(int id) {
		return employeeServ.employees()
				.stream().filter( e -> id == e.getCompany().getId())
				.collect(Collectors.toList());
	}

	@Override
	public Company getCompanyById(int id) {
		Company company = null;
		if(companyRepo.existsById(id)) 
			company =  companyRepo.findById(id).get();
		else {
			try {
				throw new NotFoundException("No such company with provided id => "+id);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " " + e.getClass() + " " + e.getStackTrace());
			}
		}
		return company;
	}

}
