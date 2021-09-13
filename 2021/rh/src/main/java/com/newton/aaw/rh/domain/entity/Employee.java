package com.newton.aaw.rh.domain.entity;

import java.time.LocalDate;

import com.newton.aaw.rh.api.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private Integer id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private LocalDate startDate;
	private LocalDate endDate;
	private String position;
	private Float monthlySalary;
	private Float hourSalary;
	private String area;
	
	public Employee(EmployeeDto employeeDto) {
		this.id = employeeDto.getId();
		this.firstName = employeeDto.getFirstName();
		this.lastName = employeeDto.getLastName();
		this.position = employeeDto.getPosition();
		this.monthlySalary = employeeDto.getMonthlySalary();
		this.hourSalary = employeeDto.getHourSalary();
		this.area = employeeDto.getArea();
	}
}
