package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-23T22:49:59+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setFirtName( employeeDto.getFirtName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setEmail( employeeDto.getEmail() );

        return employee;
    }

    @Override
    public EmployeeDto toEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setFirtName( employee.getFirtName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setEmail( employee.getEmail() );

        return employeeDto;
    }

    @Override
    public void updateEmployeeFromDto(EmployeeDto employeeDto, Employee employee) {
        if ( employeeDto == null ) {
            return;
        }

        if ( employeeDto.getId() != null ) {
            employee.setId( employeeDto.getId() );
        }
        if ( employeeDto.getFirtName() != null ) {
            employee.setFirtName( employeeDto.getFirtName() );
        }
        if ( employeeDto.getLastName() != null ) {
            employee.setLastName( employeeDto.getLastName() );
        }
        if ( employeeDto.getEmail() != null ) {
            employee.setEmail( employeeDto.getEmail() );
        }
    }
}
