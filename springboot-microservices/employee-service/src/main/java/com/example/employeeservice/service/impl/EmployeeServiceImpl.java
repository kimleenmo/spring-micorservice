package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.CustomBadRequestException;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  private void validateEmail(String email) {
    if (employeeRepository.existsByEmail(email)) {
      throw getEmailAlreadyException(email);
    }
  }

  private ResourceNotFoundException getResourceNotFoundException(Long id) {
    return new ResourceNotFoundException("Employee with id: " + id + " not found");
  }

  private CustomBadRequestException getEmailAlreadyException(String email) {
    return new CustomBadRequestException("Employee with email: " + email + " already exists");
  }

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    validateEmail(employeeDto.getEmail());

    Employee employee = employeeMapper.toEmployee(employeeDto);
    employee = employeeRepository.save(employee);
    return employeeMapper.toEmployeeDto(employee);
  }

  @Transactional(readOnly = true)
  @Override
  public Slice<EmployeeDto> findAllEmployees(Pageable pageable) {
    return employeeRepository.findAllBy(pageable).map(employeeMapper::toEmployeeDto);
  }

  @Transactional(readOnly = true)
  @Override
  public EmployeeDto getEmployeeById(Long id) {
    return employeeRepository.findById(id).map(employeeMapper::toEmployeeDto)
        .orElseThrow(() -> getResourceNotFoundException(id));
  }

  @Override
  public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> getResourceNotFoundException(id));

    String email = employeeDto.getEmail();
    if (email != null && !email.equalsIgnoreCase(employee.getEmail())
        && employeeRepository.existsByEmail(
        email)) {
      throw getEmailAlreadyException(email);
    }

    employeeMapper.updateEmployeeFromDto(employeeDto, employee);
    return employeeMapper.toEmployeeDto(employee);
  }

  @Override
  public void deleteEmployeeById(Long id) {
    int count = employeeRepository.customeDeleteById(id);
    if (count == 0) {
      throw getResourceNotFoundException(id);
    }
  }
}
