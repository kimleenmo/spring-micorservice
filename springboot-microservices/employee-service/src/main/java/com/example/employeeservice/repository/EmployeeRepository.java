package com.example.employeeservice.repository;

import com.example.employeeservice.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Slice<Employee> findAllBy(Pageable pageable);

  boolean existsByEmail(String email);

  @Modifying
  @Query(value = """
      delete from employees d where d.id = :id
      """, nativeQuery = true)
  int customeDeleteById(@Param(value = "id") Long id);
}
