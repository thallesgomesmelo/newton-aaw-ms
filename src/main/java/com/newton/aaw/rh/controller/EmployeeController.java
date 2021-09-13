package com.newton.aaw.rh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.rh.api.EmployeeDto;
import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.NotFoundException;
import com.newton.aaw.rh.service.EmployeeService;

@RestController
public class EmployeeController {
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<EmployeeDto> getAll() {
		var employees = employeeService.getAll();
		var employeeDtos = new ArrayList<EmployeeDto>();
		
		for(var employee: employees) {
			employeeDtos.add(new EmployeeDto(employee));
		}
		
		return employeeDtos;
	}
	
	@PostMapping("/employees")
	public EmployeeDto create(@RequestBody EmployeeDto em) {
		var employee = new Employee(em);
		
		employee = employeeService.create(employee);
		
		return new EmployeeDto(employee);
	}
	
	@PutMapping("/employees/{id}")
	public EmployeeDto update(@PathVariable Integer id, @RequestBody EmployeeDto em) throws NotFoundException {
		var employee = new Employee(em);
		
		employee = employeeService.update(id, employee);
		
		return new EmployeeDto(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws NotFoundException {
		employeeService.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
