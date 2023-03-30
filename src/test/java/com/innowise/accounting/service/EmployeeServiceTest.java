package com.innowise.accounting.service;

import com.innowise.accounting.dao.impl.EmployeeDaoImpl;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.entity.Role;
import com.innowise.accounting.service.impl.EmployeeServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    private Employee testEmployee;
    @Mock
    private EmployeeDaoImpl employeeDao;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @BeforeEach
    void setUp() {
        testEmployee = Employee.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2012, 8, 8))
                .role(Role.USER)
                .build();
    }

    @Test
    @SneakyThrows
    void findAllEmployeesShouldReturnList() {
        List<Employee> employees = List.of(testEmployee);

        doReturn(employees).when(employeeDao).findAll();

        List<EmployeeReadDto> employeeDtos = employeeService.findAll();
        assertThat(employeeDtos).isNotEmpty();
    }

    @Test
    @SneakyThrows
    void findEmployeeByIdShouldReturnEmployee() {
        doReturn(Optional.of(testEmployee)).when(employeeDao).findById(1L);

        Optional<EmployeeReadDto> optionalEmployee = employeeService.findById(1L);
        assertThat(optionalEmployee).isPresent();
    }

    @Test
    @SneakyThrows
    void deleteEmployeeShouldReturnTrue() {
        doReturn(true).when(employeeDao).delete(anyLong());

        assertTrue(employeeService.delete(1L));
    }

    @Test
    @SneakyThrows
    void updateEmployeeShouldReturnTrue() {
        EmployeeUpdateDto employee = EmployeeUpdateDto.builder()
                .email("test@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2012, 8, 8))
                .role(Role.USER)
                .build();

        doReturn(true).when(employeeDao).update(any(Employee.class));

        assertTrue(employeeService.update(employee));
    }

    @Test
    @SneakyThrows
    void saveEmployeeShouldReturnEmployee() {
        doReturn(testEmployee).when(employeeDao).save(any(Employee.class));

        EmployeeSaveDto employee = EmployeeSaveDto.builder()
                .email("test@gmail.com")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .birthday(LocalDate.of(2012, 8, 8))
                .role(Role.USER)
                .build();

        EmployeeReadDto optionalEmployee = employeeService.save(employee);
        assertThat(optionalEmployee.getId()).isNotZero();
    }
}
