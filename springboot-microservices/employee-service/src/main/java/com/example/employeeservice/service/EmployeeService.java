package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EmployeeService {

  EmployeeDto createEmployee(EmployeeDto employeeDto);

  Slice<EmployeeDto> findAllEmployees(Pageable pageable);

  EmployeeDto getEmployeeById(Long id);

  EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto);

  void deleteEmployeeById(Long id);
}
