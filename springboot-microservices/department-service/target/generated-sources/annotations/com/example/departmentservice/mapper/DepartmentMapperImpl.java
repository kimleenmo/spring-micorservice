package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-23T21:00:34+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDto.getId() );
        department.setDepartmentName( departmentDto.getDepartmentName() );
        department.setDepartmentDescription( departmentDto.getDepartmentDescription() );
        department.setDepartmentCode( departmentDto.getDepartmentCode() );

        return department;
    }

    @Override
    public DepartmentDto toDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId( department.getId() );
        departmentDto.setDepartmentName( department.getDepartmentName() );
        departmentDto.setDepartmentDescription( department.getDepartmentDescription() );
        departmentDto.setDepartmentCode( department.getDepartmentCode() );

        return departmentDto;
    }

    @Override
    public void updateDepartmentFromDto(DepartmentDto departmentDto, Department department) {
        if ( departmentDto == null ) {
            return;
        }

        if ( departmentDto.getId() != null ) {
            department.setId( departmentDto.getId() );
        }
        if ( departmentDto.getDepartmentName() != null ) {
            department.setDepartmentName( departmentDto.getDepartmentName() );
        }
        if ( departmentDto.getDepartmentDescription() != null ) {
            department.setDepartmentDescription( departmentDto.getDepartmentDescription() );
        }
        if ( departmentDto.getDepartmentCode() != null ) {
            department.setDepartmentCode( departmentDto.getDepartmentCode() );
        }
    }
}
