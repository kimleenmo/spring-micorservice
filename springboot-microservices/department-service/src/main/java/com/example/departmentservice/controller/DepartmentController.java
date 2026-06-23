package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentService departmentService;

  @PostMapping
  public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(departmentService.saveDepartment(departmentDto));
  }

//  @GetMapping("{id}")
//  private ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id) {
//    return ResponseEntity.ok(departmentService.getDepartmentById(id));
//  }

  @GetMapping("{code}")
  public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String code) {
    return ResponseEntity.ok(departmentService.getDepartmentByCode(code));
  }

  @GetMapping
  public ResponseEntity<Slice<DepartmentDto>> getAllDepartments(
      @PageableDefault(size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
    return ResponseEntity.ok(departmentService.getAllDepartments(pageable));
  }

  @PutMapping("{id}")
  public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,
      @RequestBody DepartmentDto departmentDto) {
    return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable Long id) {
    departmentService.deleteDepartmentById(id);
    return ResponseEntity.ok(Collections.singletonMap("message", "Department has been deleted"));
  }
}
