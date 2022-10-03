package com.sanjeet.demo.service;


import java.util.List;
import com.sanjeet.demo.entity.Employee;

public interface EmployeeServiceInterface {

	public Employee addempdtls(Employee emp);

	public List<Employee> getallempdtls();

	

	public Employee getEmpById(Long empId);



	public void deleteEmpById(Long empId);


	
	

}
