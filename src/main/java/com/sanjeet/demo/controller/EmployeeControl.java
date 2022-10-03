package com.sanjeet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjeet.demo.customexception.BuisnessException;
import com.sanjeet.demo.customexception.ControllerException;
import com.sanjeet.demo.entity.Employee;
//import com.sanjeet.demo.repository.EmployeeRepository;
//import com.sanjeet.demo.service.EmploeeServiceImpl;
import com.sanjeet.demo.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/crud")
public class EmployeeControl {

	
	@Autowired 
	private EmployeeServiceInterface empserviceinterface;
	

	@PostMapping("/addempdtls")
	//public ResponseEntity<Employee> addempdtls(@RequestBody Employee emp)
	public ResponseEntity<?> addempdtls(@RequestBody Employee emp)
	{
		try {

		Employee empsave=empserviceinterface.addempdtls(emp);
		return new ResponseEntity<Employee>(empsave,HttpStatus.CREATED);
		}catch(BuisnessException e) {
			ControllerException ce=new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e)
		{
			ControllerException ce=new ControllerException("611","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		
		}
	
	@GetMapping("/getallempdtls")
	public ResponseEntity<List<Employee>> getallempdtls()
	{
        List<Employee> listemp=  empserviceinterface.getallempdtls();	
        System.out.println("getting all employee details");
        return new ResponseEntity<List<Employee>>(listemp, HttpStatus.OK);
	}
	
	@GetMapping("/getempdtls/{emp_id}")
	public ResponseEntity<?> getEmpById(@PathVariable("emp_id") Long EmpId)
	{
		try {
		Employee empbyid=empserviceinterface.getEmpById(EmpId);
		return new ResponseEntity<Employee>(empbyid, HttpStatus.OK);
	      }catch(BuisnessException e) {
				ControllerException ce=new ControllerException(e.getErrorCode(),e.getErrorMessage());
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}catch(Exception e)
			{
				ControllerException ce=new ControllerException("613","Something went wrong in controller");
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}
	}
	
	@DeleteMapping("/deleteempdtls/{emp_id}")
	public ResponseEntity<Void> DeleteEmpById(@PathVariable("emp_id") Long EmpId)
	{
		empserviceinterface.deleteEmpById(EmpId); 	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/updateempdtls")
	public ResponseEntity<Employee> updateempdtls(@RequestBody Employee emp)
	{
		Employee empsave=empserviceinterface.addempdtls(emp);
		return new ResponseEntity<Employee>(empsave,HttpStatus.CREATED);
	}	
			
}
