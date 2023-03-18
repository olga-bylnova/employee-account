package com.innowise.accounting.mapper;

import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.entity.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeDtoMapper {
    EmployeeReadDto employeeToReadDto(Employee employee);
    Employee employeeReadDtoToEmployee(EmployeeReadDto employeeReadDto);

    EmployeeSaveDto employeeToSaveDto(Employee employee);
    Employee employeeSaveDtoToEmployee(EmployeeSaveDto employeeSaveDto);

    EmployeeUpdateDto employeeToUpdateDto(Employee employee);
    Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
}
