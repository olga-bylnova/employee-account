package com.innowise.accounting.dao;

import com.innowise.accounting.dao.impl.EmployeeDaoImpl;
import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.entity.Role;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoTest {
    private final EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();

    @Test
    @SneakyThrows
    void saveEmployee() {
        Employee testEmployee = Employee.builder()
                .email("test@gmail.com")
                .password("123")
                .firstName("Test")
                .lastName("Testov")
                .birthday(LocalDate.of(2003, 3, 29))
                .role(Role.USER)
                .build();

        Employee savedEmployee = employeeDao.save(testEmployee);
        assertTrue(savedEmployee.getId() != 0);
    }

    @Test
    @SneakyThrows
    void findAll() {
        List<Employee> employeeList = employeeDao.findAll();
        assertNotNull(employeeList);
    }

    @Test
    @SneakyThrows
    void findEmployeeById() {
        Employee testEmployee = Employee.builder()
                .email("test1@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2003, 3, 29))
                .role(Role.USER)
                .build();
        Employee savedEmployee = employeeDao.save(testEmployee);

        Optional<Employee> employee = employeeDao.findById(savedEmployee.getId());
        assertThat(employee).isPresent();
    }

    @Test
    @SneakyThrows
    void findEmployeeByEmail() {
        Optional<Employee> employee = employeeDao.findByEmail("test@gmail.com");
        assertThat(employee).isPresent();
    }

    @Test
    @SneakyThrows
    void updateEmployee() {
        Employee testEmployee = Employee.builder()
                .email("testSave@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2003, 3, 29))
                .role(Role.USER)
                .build();
        Employee savedEmployee = employeeDao.save(testEmployee);
        savedEmployee.setEmail("testUpdate@gmail.com");

        assertTrue(employeeDao.update(savedEmployee));
    }

    @Test
    @SneakyThrows
    void deleteEmployee() {
        Employee testEmployee = Employee.builder()
                .email("testDelete@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2003, 3, 29))
                .role(Role.USER)
                .build();
        Employee savedEmployee = employeeDao.save(testEmployee);

        assertTrue(employeeDao.delete(savedEmployee.getId()));
    }
}
