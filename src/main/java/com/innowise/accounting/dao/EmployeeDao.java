package com.innowise.accounting.dao;

import com.innowise.accounting.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    boolean delete(Long id);
    boolean update(Employee employee);
    Employee save(Employee employee);
    Optional<Employee> findByEmail(String email);
}
