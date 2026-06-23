package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

  Department toDepartment(DepartmentDto departmentDto);

  DepartmentDto toDepartmentDto(Department department);

  void updateDepartmentFromDto(DepartmentDto departmentDto, @MappingTarget Department department);
}
