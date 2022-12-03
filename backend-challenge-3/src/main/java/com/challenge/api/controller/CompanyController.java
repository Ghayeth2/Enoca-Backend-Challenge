package com.challenge.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.api.model.Company;
import com.challenge.api.service.CompanyService;

@RestController
@RequestMapping("rest-api/company")
public class CompanyController {
	@Autowired private CompanyService companyService;
	
	@PostMapping("/new")
	public ResponseEntity<Company> saveCompany(@RequestBody Company company){
		return new ResponseEntity<Company>(companyService.saveCompany(company), HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable("id") int id){
		return new ResponseEntity<Company>(companyService.updateCompany(company,id), HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> deleteCompany(@PathVariable("id") int id){
		return new ResponseEntity<Integer>(companyService.deleteCompany(id), HttpStatus.OK);
	}
	@GetMapping("")
	public List<Company> listCompany(){
		return companyService.companies();
	}
}
