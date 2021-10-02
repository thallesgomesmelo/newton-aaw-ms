package com.newton.aaw.rh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.domain.repository.EmployeeRepository;
import com.newton.aaw.rh.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {	
	
	private final EmployeeRepository employeeRepository;
	
//	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	public Employee create(Employee em) {
		var now = LocalDateTime.now();
		
		em.setCreateAt(now);
		em.setModifiedAt(now);
		
		employeeRepository.save(em);
		
		return em;
	}
	
	public Employee update(String id, Employee em) {
		var existing = get(id);
		
		existing.setFirstName(em.getFirstName());
		existing.setLastName(em.getLastName());
		existing.setDateOfBirth(em.getDateOfBirth());
		existing.setGender(em.getGender());
		existing.setStartDate(em.getStartDate());
		existing.setEndDate(em.getEndDate());
		existing.setPosition(em.getPosition());
		existing.setMonthlySalary(em.getMonthlySalary());
		existing.setHourSalary(em.getHourSalary());
		existing.setArea(em.getArea());
		
		existing.setModifiedAt(LocalDateTime.now());
		
		employeeRepository.save(existing);
		return existing;
	}
	
	public Employee get(String id) {
		var employee = employeeRepository.findById(id);
		
		if(employee.isEmpty()) {
			throw new NotFoundException("Employee with ID " + id + " not found"); 
		}
		
		return employee.get();
	}
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}
	
	public void delete(String id) {
		get(id);
		employeeRepository.deleteById(id);
	}
}
