package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeLoginDto;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.AttributeName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static com.innowise.accounting.util.AttributeName.ROLE;
import static com.innowise.accounting.util.ResponseMessage.INVALID_CREDENTIALS;
import static com.innowise.accounting.util.ResponseMessage.USER_LOGGED_IN;
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

                HttpSession session = req.getSession();
                session.setAttribute(ROLE, employeeDto.getRole().name());
                writeResponse(resp, USER_LOGGED_IN, HttpServletResponse.SC_OK);
            } else {
                writeResponse(resp, INVALID_CREDENTIALS, HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
