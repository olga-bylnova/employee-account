package com.innowise.accounting.service;

import com.innowise.accounting.dto.EmployeeLoginDto;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeReadDto> findAll() throws ServiceException;
    Optional<EmployeeReadDto> findById(Long id) throws ServiceException;
    boolean delete(Long id) throws ServiceException;
    boolean update(EmployeeUpdateDto employeeUpdateDto) throws ServiceException;
    EmployeeReadDto save(EmployeeSaveDto employeeSaveDto) throws ServiceException;
    Optional<EmployeeReadDto> login(EmployeeLoginDto loginDto) throws ServiceException;
}
