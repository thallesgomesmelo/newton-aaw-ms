package com.newton.aaw.rh.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.newton.aaw.rh.api.EmployeeDto;
import com.newton.aaw.rh.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data    //Agrupa varias anotacoe em uma so
@With    //Permite construir atributos parciais, não precisa informar todos os atributos do employee, so o nome ou lasname ja aceita
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
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
	
	public Employee(EmployeeDto em) {
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
	}
}
