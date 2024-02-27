package com.ems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.backend.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(Long employeeId);
	List<EmployeeDto> getAllEmployee();
	void deleteEmployeeById(Long employeeId);
	 EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

}
