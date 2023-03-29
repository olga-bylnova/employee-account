package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.innowise.accounting.util.ResponseMessage.UNABLE_TO_FIND_EMPLOYEES;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class GetEmployeesRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<EmployeeReadDto> employees = service.findAll();

            if (!employees.isEmpty()) {
                String json = objectMapper.writeValueAsString(employees);
                writeResponse(resp, json, HttpServletResponse.SC_OK);
            } else {
                writeResponse(resp, UNABLE_TO_FIND_EMPLOYEES, HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
