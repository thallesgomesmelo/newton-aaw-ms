package com.newton.aaw.rh.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth; //yyyy-mm-dd
	private Gender gender;
	private LocalDate startDate;
	private LocalDate endDate;
	private String position;
	private Float monthlySalary;
	private Float hourSalary;
	private String area;
	private LocalDateTime createAt; //yyyy-mm-dd hh:mm:ss
	private LocalDateTime modifiedAt;
	
	public EmployeeDto(Employee em) {
		this.id = em.getId();
		this.firstName = em.getFirstName();
		this.lastName = em.getLastName();
		this.dateOfBirth = em.getDateOfBirth();
		this.gender = em.getGender();
		this.startDate = em.getStartDate();
		this.endDate = em.getEndDate();
		this.position = em.getPosition();
		this.monthlySalary = em.getMonthlySalary();
		this.hourSalary = em.getHourSalary();
		this.area = em.getArea();
		
		this.createAt = em.getCreateAt();
		this.modifiedAt = em.getModifiedAt();
	}
}
