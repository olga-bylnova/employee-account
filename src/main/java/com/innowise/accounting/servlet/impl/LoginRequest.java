package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeLoginDto;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.ResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final EmployeeService service = new EmployeeServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeLoginDto loginDto = objectMapper.readValue(req.getReader(), EmployeeLoginDto.class);
        var readDto = service.login(loginDto);

        if (readDto.isPresent()) {
            String json = objectMapper.writeValueAsString(readDto);
            ResponseWriter.writeResponse(resp, json, HttpServletResponse.SC_OK);
        }
    }
}
