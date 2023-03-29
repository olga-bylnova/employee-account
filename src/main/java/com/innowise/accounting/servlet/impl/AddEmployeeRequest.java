package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class AddEmployeeRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EmployeeSaveDto saveDto = objectMapper.readValue(req.getReader(), EmployeeSaveDto.class);
            EmployeeReadDto readDto = service.save(saveDto);
            //here we return optional?
            //if it's absent -> return bad request status
            //TODO
            String json = objectMapper.writeValueAsString(readDto);
            writeResponse(resp, json, HttpServletResponse.SC_CREATED);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
