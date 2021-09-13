package com.newton.aaw.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.exception.NotFoundException;

@Service
public class EmployeeService {
	private static int id=0;
	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	public Employee create(Employee em) {
		em.setId(++id);
		
		employees.put(id, em);
		
		return em;
	}
	
	public Employee update(Integer id, Employee em) throws NotFoundException {
		var existing = get(id);
		
		existing.setFirstName(em.getFirstName());
		existing.setLastName(em.getLastName());
		existing.setPosition(em.getPosition());
		existing.setMonthlySalary(em.getMonthlySalary());
		existing.setHourSalary(em.getHourSalary());
		existing.setArea(em.getArea());
		
		return existing;
	}
	
	public Employee get(Integer id) throws NotFoundException {
		var employee = employees.get(id);
		
		if(employee == null) {
			throw new NotFoundException("Employee with ID " + id + " not found"); 
		}
		
		return employee;
	}
	
	public List<Employee> getAll(){
		return new ArrayList<>(employees.values());
	}
	
	public void delete(Integer id) throws NotFoundException {
		get(id);
		employees.remove(id);
	}
}
