package com.ems.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.backend.dto.EmployeeDto;
import com.ems.backend.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	
	//Add Employee RestApi
	@PostMapping("/saveEmployee")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
		
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
		
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id")Long employeeId){
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(employeeDto);		
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAllEmployees(){
		List<EmployeeDto> employeeList = employeeService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	
	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployeeById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted with given id :"+employeeId);
	}
	
	 @PutMapping("{id}")
	  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
	                                                      @RequestBody EmployeeDto updatedEmployee){
	          EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
	          return ResponseEntity.ok(employeeDto);
	    }
	
	
}
