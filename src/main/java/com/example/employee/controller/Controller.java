package com.example.employee.controller;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.repository.Repository;

@RestController
@RequestMapping(path="/employee")
public class Controller {
	@Autowired
	private Repository repository;
	
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/v2")
	public  List<Employee> getEmployeelist(){
		return (repository.findAll());
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/v1")
	public  void createEmployee(@RequestBody Employee employee)
	{
		repository.save(employee);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee=repository.findById(id).orElseThrow();
		return ResponseEntity.ok(employee);		
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@PutMapping("/v3/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) throws AttributeNotFoundException{
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee = repository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@DeleteMapping("/del/{id}")
	public void deleteById( @PathVariable Long id) throws AttributeNotFoundException
	{
		
	
			Employee employee1 = repository.findById(id)
					.orElseThrow(() -> new AttributeNotFoundException("Employee not exist with id :" + id));
			
		repository.deleteById(id);
	}
}

