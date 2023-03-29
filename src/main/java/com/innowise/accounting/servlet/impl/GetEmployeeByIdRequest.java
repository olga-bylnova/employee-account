package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.IdParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.innowise.accounting.util.ResponseMessage.EMPLOYEE_NOT_FOUND;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class GetEmployeeByIdRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = IdParser.parseId(req.getRequestURI());
            Optional<EmployeeReadDto> employeeDto = service.findById(id);

            if (employeeDto.isPresent()) {
                String json = objectMapper.writeValueAsString(employeeDto.get());
                writeResponse(resp, json, HttpServletResponse.SC_OK);
            } else {
                writeResponse(resp, EMPLOYEE_NOT_FOUND, HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
