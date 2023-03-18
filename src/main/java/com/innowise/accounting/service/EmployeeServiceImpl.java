package com.innowise.accounting.service;

import com.innowise.accounting.dao.impl.EmployeeDaoImpl;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.mapper.EmployeeDtoMapper;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private static final EmployeeServiceImpl INSTANCE = new EmployeeServiceImpl();
    private static final EmployeeDaoImpl employeeDao = EmployeeDaoImpl.getInstance();
    private static final EmployeeDtoMapper mapper = EmployeeDtoMapper.INSTANCE;

    public static EmployeeServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<EmployeeReadDto> findAll() {
        return employeeDao.findAll()
                .stream()
                .map(mapper::employeeToReadDto)
                .toList();
    }

    @Override
    public Optional<EmployeeReadDto> findById(Long id) {
        return employeeDao.findById(id)
                .map(mapper::employeeToReadDto);
    }

    @Override
    public boolean delete(Long id) {
        return employeeDao.delete(id);
    }

    @Override
    public boolean update(EmployeeUpdateDto employeeUpdateDto) {
        return employeeDao.update(mapper.employeeUpdateDtoToEmployee(employeeUpdateDto));
    }

    @Override
    public EmployeeReadDto save(EmployeeSaveDto employeeSaveDto) {
        Employee employee = employeeDao.save(mapper.employeeSaveDtoToEmployee(employeeSaveDto));
        return mapper.employeeToReadDto(employee);
    }
}
