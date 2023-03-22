package com.innowise.accounting.service;

import com.innowise.accounting.dao.impl.EmployeeDaoImpl;
import com.innowise.accounting.dto.EmployeeLoginDto;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.exception.DaoException;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.mapper.EmployeeDtoMapper;
import com.innowise.accounting.util.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private static final EmployeeServiceImpl INSTANCE = new EmployeeServiceImpl();
    private static final EmployeeDaoImpl employeeDao = EmployeeDaoImpl.getInstance();
    private static final EmployeeDtoMapper mapper = EmployeeDtoMapper.INSTANCE;
    private static final PasswordEncoder encoder = PasswordEncoder.getInstance();

    public static EmployeeServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<EmployeeReadDto> findAll() throws ServiceException {
        try {
            return employeeDao.findAll()
                    .stream()
                    .map(mapper::employeeToReadDto)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<EmployeeReadDto> findById(Long id) throws ServiceException {
        try {
            return employeeDao.findById(id)
                    .map(mapper::employeeToReadDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return employeeDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(EmployeeUpdateDto employeeUpdateDto) throws ServiceException {
        try {
            return employeeDao.update(mapper.employeeUpdateDtoToEmployee(employeeUpdateDto));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public EmployeeReadDto save(EmployeeSaveDto employeeSaveDto) throws ServiceException {
        try {
            employeeSaveDto.setPassword(encoder.encode(employeeSaveDto.getPassword()));
            Employee employee = employeeDao.save(mapper.employeeSaveDtoToEmployee(employeeSaveDto));
            return mapper.employeeToReadDto(employee);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<EmployeeReadDto> login(EmployeeLoginDto loginDto) throws ServiceException {
        Optional<EmployeeReadDto> readDto = Optional.empty();
        Optional<Employee> employeeOptional;
        try {
            employeeOptional = employeeDao.findByEmail(loginDto.getEmail());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (encoder.verify(employee.getPassword(), loginDto.getPassword())) {
                readDto = Optional.of(mapper.employeeToReadDto(employee));
            }
        }
        return readDto;
    }
}
