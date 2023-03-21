
package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.dto.EmployeeSaveDto;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.IdParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class UpdateEmployeeRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeService service = new EmployeeServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeUpdateDto updateDto = objectMapper.readValue(req.getReader(), EmployeeUpdateDto.class);
        long id = IdParser.parseId(req.getRequestURI());
        updateDto.setId(id);
        if (service.update(updateDto)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
