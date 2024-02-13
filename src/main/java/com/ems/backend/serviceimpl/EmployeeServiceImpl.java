package com.ems.backend.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ems.backend.dto.EmployeeDto;
import com.ems.backend.exception.ResourceNotFoundException;
import com.ems.backend.mapper.EmployeeMapper;
import com.ems.backend.model.Employee;
import com.ems.backend.repos.EmployeeRepository;
import com.ems.backend.service.EmployeeService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
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

}
