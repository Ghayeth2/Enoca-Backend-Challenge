package com.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.api.model.Employee;
import com.challenge.api.service.CompanyService;
import com.challenge.api.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeWebController {
	
	@Autowired private EmployeeService employeeService;
	@Autowired private CompanyService companyService;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("employees", employeeService.employees());
		return "eIndex";
	}
	@GetMapping("/new")  
	public String newEmployee(Model model) {
		model.addAttribute("companies", companyService.companies());
		return "employeeNew";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employee";
	}
	@GetMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") int id, Model model) {
		model.addAttribute("companies",companyService.companies());
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "editEmployee";
	}
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee) {
		employeeService.updatEmployee(employee, id);
		return "redirect:/employee";
	}
	@PostMapping("/save")
	public String saveEmployee(Employee employee) {
		employeeService.savEmployee(employee);
		return "redirect:/employee";
	}
}
