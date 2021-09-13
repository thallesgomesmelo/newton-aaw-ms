package com.newton.aaw.rh.api;

import java.time.LocalDate;

import com.newton.aaw.rh.domain.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
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
	
	public EmployeeDto(Employee em) {
		this.id = em.getId();
		this.firstName = em.getFirstName();
		this.lastName = em.getLastName();
		this.dateOfBirth = em.getDateOfBirth();
		this.startDate = em.getStartDate();
		this.endDate = em.getEndDate();
		this.position = em.getPosition();
		this.monthlySalary = em.getMonthlySalary();
		this.hourSalary = em.getHourSalary();
		this.area = em.getArea();
	}
}
