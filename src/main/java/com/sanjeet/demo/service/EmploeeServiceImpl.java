package com.sanjeet.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjeet.demo.customexception.BuisnessException;
import com.sanjeet.demo.entity.Employee;
import com.sanjeet.demo.repository.EmployeeRepository;

@Service
public class EmploeeServiceImpl implements EmployeeServiceInterface{

	@Autowired
	private EmployeeRepository emprepository;

	@Override
	public Employee addempdtls(Employee emp) {
	
	     if(emp.getName().isEmpty() || emp.getName().length() == 0)	{
	    	 throw new BuisnessException("601", "Please send proper name"); 
	     }
	    try     
	    {
		Employee empsave=emprepository.save(emp);
		return empsave;
	}catch (IllegalArgumentException e) {
		throw new BuisnessException("602","given employee is null" + e.getMessage());
	}catch(Exception e)
	{
		throw new BuisnessException("603","something went wrong in servicelayer while adding employee" + e.getMessage());
	}
	}
	

	@Override
	public List<Employee> getallempdtls() {
		// TODO Auto-generated method stub
		List<Employee> emplist=null;
		emplist=emprepository.findAll();
		
		if (emplist.isEmpty())
			throw new BuisnessException("604", "hey list is empty nothing to return");
		try {
			return emplist;
		}catch(Exception e)
		{
          throw new BuisnessException("605", "something went wrong in servicelayer while fetching all employee" + e.getMessage());	
		}
		
	}

	@Override
	public Employee getEmpById(Long empId) {	
		try {
		 return emprepository.findById(empId).get();
	}catch(IllegalArgumentException e) {
    throw new BuisnessException("606", "please check the id .it is null to fetch empdtls" + e.getMessage());
	}catch(NoSuchElementException e) {
		throw new BuisnessException("607", "employee with id doesn't exits in database" +e.getMessage());
	}
	}

	@Override
	public void deleteEmpById(Long empId) {
		// TODO Auto-generated method stub
		try {
		emprepository.deleteById(empId);
	}catch(IllegalArgumentException e) {
	    throw new BuisnessException("608", "please check the id .it is null for emp delete" + e.getMessage());
		}catch(NoSuchElementException e) {
			throw new BuisnessException("609", "employee with id doesn't exits in database for deletion" +e.getMessage());
		}
		}
}
