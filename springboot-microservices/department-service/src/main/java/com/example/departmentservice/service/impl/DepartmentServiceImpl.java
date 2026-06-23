package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.CustomBadRequestException;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.mapper.DepartmentMapper;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;
  private final DepartmentMapper departmentMapper;

  @Override
  public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
    if (departmentRepository.existsByDepartmentCode(departmentDto.getDepartmentCode())) {
      throw new CustomBadRequestException(
          "Department code " + departmentDto.getDepartmentCode() + " already exists");
    }

    if (departmentRepository.existsByDepartmentName(departmentDto.getDepartmentName())) {
      throw new CustomBadRequestException(
          "Department name " + departmentDto.getDepartmentName() + " already exists");
    }

    Department department = departmentMapper.toDepartment(departmentDto);
    department = departmentRepository.save(department);
    return departmentMapper.toDepartmentDto(department);
  }

  @Transactional(readOnly = true)
  @Override
  public DepartmentDto getDepartmentById(Long id) {
    return departmentRepository.findById(id).map(departmentMapper::toDepartmentDto)
        .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
  }

  @Transactional(readOnly = true)
  @Override
  public DepartmentDto getDepartmentByCode(String departmentCode) {
    return departmentRepository.findByDepartmentCode(departmentCode)
        .map(departmentMapper::toDepartmentDto)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Department not found with code " + departmentCode));
  }

  @Transactional(readOnly = true)
  @Override
  public Slice<DepartmentDto> getAllDepartments(Pageable pageable) {
    return departmentRepository.findAll(pageable).map(departmentMapper::toDepartmentDto);
  }

  @Override
  public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
    Department department = departmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));

    String code = departmentDto.getDepartmentCode();
    if (code != null && !code
        .equals(department.getDepartmentCode()) && departmentRepository.existsByDepartmentCode(
        code)) {
      throw new CustomBadRequestException(
          "Department code " + code + " already exists");
    }

    String name = departmentDto.getDepartmentName();
    if (name != null && !name
        .equalsIgnoreCase(department.getDepartmentName())
        && departmentRepository.existsByDepartmentName(name)) {
      throw new CustomBadRequestException(
          "Department name " + name + " already exists");
    }

    departmentMapper.updateDepartmentFromDto(departmentDto, department);
    department = departmentRepository.save(department);
    return departmentMapper.toDepartmentDto(department);
  }

  @Override
  public void deleteDepartmentById(Long id) {
    int count = departmentRepository.customDeleteById(id);
    if (count == 0) {
      throw new ResourceNotFoundException("Department not found with id " + id);
    }
  }
}
