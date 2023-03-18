package com.innowise.accounting.service;

import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeReadDto> findAll();
    Optional<EmployeeReadDto> findById(Long id);
    boolean delete(Long id);
    boolean update(EmployeeUpdateDto employeeUpdateDto);
    EmployeeReadDto save(EmployeeSaveDto employeeSaveDto);
}
