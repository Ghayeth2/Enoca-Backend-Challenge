package com.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.api.model.Company;
import com.challenge.api.service.CompanyService;

  
@Controller
@RequestMapping("/company")
public class CompanyWebController {
	
	@Autowired private CompanyService companyService;
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("companies", companyService.companies());
		return "cIndex";
	}
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public String companyEmployees(@PathVariable("id") int id, Model model) {
		model.addAttribute("cName", companyService.getCompanyById(id).getName());
		model.addAttribute("employees", companyService.companyEmployees(id));
		return "cEmployees";
	}
	@GetMapping("/new")  
	public String newCompany() {
		return "company";
	}
	@GetMapping("/delete/{id}")
	public String deleteCompany(@PathVariable("id") int id) {
		companyService.deleteCompany(id);
		return "redirect:/company";
	}
	@GetMapping("/edit/{id}")
	public String editCompany(@PathVariable("id") int id, Model model) {
		Company company = companyService.getCompanyById(id);
		model.addAttribute("company", company);
		return "editCompany";
	}
	@PostMapping("/update/{id}")
	public String updateCompany(@PathVariable("id") int id, @ModelAttribute("company") Company company) {
		companyService.updateCompany(company, id);
		return "redirect:/company";
	}
	@PostMapping("/save")
	public String saveCompany(Company company) {
		companyService.saveCompany(company);
		return "redirect:/company";
	}
}
