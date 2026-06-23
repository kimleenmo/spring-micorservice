package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DepartmentService {

  DepartmentDto saveDepartment(DepartmentDto departmentDto);

  DepartmentDto getDepartmentById(Long id);

  DepartmentDto getDepartmentByCode(String departmentCode);

  Slice<DepartmentDto> getAllDepartments(Pageable pageable);

  DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);

  void deleteDepartmentById(Long id);
}
