package com.example.employeeservice.controller;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(employeeService.createEmployee(employeeDto));
  }

  @GetMapping("{id}")
  public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  @GetMapping
  public ResponseEntity<Slice<EmployeeDto>> getAllEmployees(@PageableDefault(size = 5,
      sort = "id", direction = Direction.ASC) Pageable pageable) {
    return ResponseEntity.ok(employeeService.findAllEmployees(pageable));
  }

  @PutMapping("{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,
      @RequestBody EmployeeDto employeeDto) {
    return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployeeById(id);
    return ResponseEntity.ok(Collections.singletonMap("message", "employee deleted"));
  }
}
