package com.howtodoinjava.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;

@Controller
public class IndexController {
	
	@Autowired
    EmployeeService service;
 
    @RequestMapping("/employeesList")
    public String getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy,Model model)
    {
    	model.addAttribute("employees", service.getAllEmployees(pageNo, pageSize, sortBy));
 
        return "list-employees";
    }
    
    @RequestMapping("/edit")
    public String createOrUpdateEmployee(Model model){
       // EmployeeEntity updated = service.createOrUpdateEmployee(employee);
    	model.addAttribute("employee",new EmployeeEntity());
        return "add-edit-employee";
    }
    
    @PostMapping("/createEmployee")
    public String createOrUpdateEmployee(@ModelAttribute("employee") @Valid EmployeeEntity employee)
                                                    throws RecordNotFoundException {
    	//employee.setId(1L);
        service.createOrUpdateEmployee(employee);
        return "redirect:/employeesList";
    }
    
    @GetMapping("/edit/{id}")
    public String getEmployeeById(@PathVariable("id") Long id,Model model)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
        model.addAttribute("employee",entity);
        return "add-edit-employee";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return "redirect:/employeesList";
    }

}
