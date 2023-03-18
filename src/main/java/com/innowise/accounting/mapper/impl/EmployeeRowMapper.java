package com.innowise.accounting.mapper.impl;

import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.entity.Role;
import com.innowise.accounting.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.innowise.accounting.util.EmployeeTableColumnName.*;

public class EmployeeRowMapper implements RowMapper<Employee> {
    private static final EmployeeRowMapper INSTANCE = new EmployeeRowMapper();

    public static EmployeeRowMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Employee> mapRow(ResultSet resultSet) {
        Optional<Employee> employeeOptional;
        try {
            Employee employee = Employee.builder()
                    .id(resultSet.getLong(ID))
                    .firstName(resultSet.getString(FIRST_NAME))
                    .lastName(resultSet.getString(LAST_NAME))
                    .birthday(resultSet.getDate(BIRTHDAY).toLocalDate())
                    .email(resultSet.getString(EMAIL))
                    .password(resultSet.getString(PASSWORD))
                    .role(Role.valueOf(resultSet.getString(ROLE)))
                    .build();
            employeeOptional = Optional.of(employee);
        } catch (SQLException e) {
            return Optional.empty();
        }
        return employeeOptional;
    }
}
