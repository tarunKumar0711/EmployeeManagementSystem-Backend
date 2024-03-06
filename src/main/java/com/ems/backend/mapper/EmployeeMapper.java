package com.ems.backend.mapper;

import com.ems.backend.dto.EmployeeDto;
import com.ems.backend.model.Employee;

public class EmployeeMapper {
	
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto =  new EmployeeDto();
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setId(employee.getId());
		employeeDto.setLastName(employee.getLastName());
		return employeeDto;
	}
	
	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		Employee employee =  new Employee();
		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setId(employeeDto.getId());
		employee.setLastName(employeeDto.getLastName());
		return employee;
	}

}
