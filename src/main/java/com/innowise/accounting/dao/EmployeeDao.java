package com.innowise.accounting.dao;

import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> findAll() throws DaoException;
    Optional<Employee> findById(Long id) throws DaoException;
    boolean delete(Long id) throws DaoException;
    boolean update(Employee employee) throws DaoException;
    Employee save(Employee employee) throws DaoException;
    Optional<Employee> findByEmail(String email) throws DaoException;
}
