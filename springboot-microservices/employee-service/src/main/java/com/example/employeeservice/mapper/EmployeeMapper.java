package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

  Employee toEmployee(EmployeeDto employeeDto);

  EmployeeDto toEmployeeDto(Employee employee);

  void updateEmployeeFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);
}
