package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

  Optional<Department> findByDepartmentCode(String departmentCode);

  boolean existsByDepartmentCode(String departmentCode);

  boolean existsByDepartmentName(String departmentName);

  @Modifying
  @Query(value = """
      delete from departments
      where id = :id
      """, nativeQuery = true)
  int customDeleteById(@Param("id") Long id);

}
