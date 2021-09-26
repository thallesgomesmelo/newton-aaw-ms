package com.newton.aaw.rh.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newton.aaw.rh.domain.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
