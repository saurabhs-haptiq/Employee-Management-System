package com.rekildo.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rekildo.entity.Employee;
import com.rekildo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		return "emp_save";
	}
	
	@GetMapping("/EditEmp/{id}")
	public String EditEmp(@PathVariable int id, Model m) {
//		System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp",emp);
		return "edit_emp";
	}

	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp,HttpSession session) {
//		System.out.println(emp);
		Employee newEmp =  empService.saveEmp(emp);
		
		if(newEmp != null) {
			System.out.println("Save Successfully");
			session.setAttribute("msg", "Register successfully");
		}else {
			System.out.println("Someting Wrong");
			session.setAttribute("msg", "Something wrong on SERVER!");
		}
		return "redirect:/loadEmpSave";
	}
	
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp,HttpSession session) {
//		System.out.println(emp);
		Employee update =  empService.saveEmp(emp);
		
		if(update != null) {
			System.out.println("Save Successfully");
			session.setAttribute("msg", "Update successfully");
		}else {
			System.out.println("Someting Wrong");
			session.setAttribute("msg", "Something wrong on SERVER!");
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if(f) {
			session.setAttribute("msg", "Deleted Successfully");
		}else {
			session.setAttribute("msg", "Deleted Successfully");
		}
		return "redirect:/";
	}
}
