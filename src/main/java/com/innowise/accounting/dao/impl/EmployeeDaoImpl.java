package com.innowise.accounting.dao.impl;

import com.innowise.accounting.dao.Dao;
import com.innowise.accounting.entity.Employee;
import com.innowise.accounting.exception.DaoException;
import com.innowise.accounting.mapper.impl.EmployeeRowMapper;
import com.innowise.accounting.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.innowise.accounting.util.EmployeeTableColumnName.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDaoImpl implements Dao<Long, Employee> {
    private static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl();
    private static final EmployeeRowMapper rowMapper = EmployeeRowMapper.getInstance();
    private static final String FIND_ALL_SQL = """
            SELECT id, first_name, last_name, birthday, email, password, role FROM employee;
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT id, first_name, last_name, birthday, email, password, role FROM employee
            WHERE id = ?;
            """;
    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM employee WHERE id = ?;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO employee (first_name, last_name, birthday, email, password, role)
            VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE employee
            SET first_name = ?, last_name = ?, birthday = ?,
            email = ?, password = ?, role = ?
            WHERE id = ?
            """;

    public static EmployeeDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Employee> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet set = statement.executeQuery();

            List<Employee> employees = new ArrayList<>();
            while (set.next()) {
                Optional<Employee> optionalEmployee = rowMapper.mapRow(set);
                optionalEmployee.ifPresent(employees::add);
            }
            return employees;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setObject(1, id);

            ResultSet set = statement.executeQuery();
            Optional<Employee> optionalEmployee = Optional.empty();
            while (set.next()) {
                optionalEmployee = rowMapper.mapRow(set);
            }
            return optionalEmployee;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setObject(1, id);

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Employee employee) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setObject(3, employee.getBirthday());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPassword());
            statement.setString(6, employee.getRole().name());
            statement.setLong(7, employee.getId());

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Employee save(Employee employee) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setObject(3, employee.getBirthday());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPassword());
            statement.setString(6, employee.getRole().name());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();

            employee.setId(generatedKeys.getLong(ID));
            return employee;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
