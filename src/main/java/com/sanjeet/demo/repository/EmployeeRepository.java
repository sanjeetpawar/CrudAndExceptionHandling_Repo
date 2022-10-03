package com.sanjeet.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjeet.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
