package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeLoginDto;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.AttributeName;
import com.innowise.accounting.util.ResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class LoginRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EmployeeLoginDto loginDto = objectMapper.readValue(req.getReader(), EmployeeLoginDto.class);
            Optional<EmployeeReadDto> readDto = service.login(loginDto);

            if (readDto.isPresent()) {
                EmployeeReadDto employeeDto = readDto.get();
                String json = objectMapper.writeValueAsString(employeeDto);
                HttpSession session = req.getSession(false);
                session.setAttribute(AttributeName.ROLE, employeeDto.getRole());
                writeResponse(resp, json, HttpServletResponse.SC_OK);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
