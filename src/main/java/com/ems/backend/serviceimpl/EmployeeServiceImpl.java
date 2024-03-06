package com.ems.backend.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ems.backend.dto.EmployeeDto;
import com.ems.backend.exception.ResourceNotFoundException;
import com.ems.backend.mapper.EmployeeMapper;
import com.ems.backend.model.Department;
import com.ems.backend.model.Employee;
import com.ems.backend.repos.DepartmentRepository;
import com.ems.backend.repos.EmployeeRepository;
import com.ems.backend.service.EmployeeService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;

	private final DepartmentRepository departmentRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Department department = departmentRepository.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id :"+ employeeDto.getDepartmentId()));
		employee.setDepartment(department);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with given id: "+employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);		
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employeesList = employeeRepository.findAll();
		
		return employeesList.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp)).collect(Collectors.toList());
	}

	@Override
	public void deleteEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with given id: "+employeeId));
		employeeRepository.deleteById(employeeId);
	}
	
	 @Override
	    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

	        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
	                () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
	        );

	        employee.setFirstName(updatedEmployee.getFirstName());
	        employee.setLastName(updatedEmployee.getLastName());
	        employee.setEmail(updatedEmployee.getEmail());

	        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
					.orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id :"+ updatedEmployee.getDepartmentId()));
			employee.setDepartment(department);
			
	        Employee updatedEmployeeObj = employeeRepository.save(employee);

	        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	    }
}
