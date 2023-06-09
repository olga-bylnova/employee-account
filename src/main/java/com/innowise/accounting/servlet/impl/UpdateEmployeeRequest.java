
package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeUpdateDto;
import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.IdParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.innowise.accounting.util.ResponseMessage.UNABLE_TO_UPDATE_EMPLOYEE;
import static com.innowise.accounting.util.ResponseMessage.USER_UPDATED;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class UpdateEmployeeRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EmployeeUpdateDto updateDto = objectMapper.readValue(req.getReader(), EmployeeUpdateDto.class);
            long id = IdParser.parseId(req.getRequestURI());
            updateDto.setId(id);

            if (service.update(updateDto)) {
                writeResponse(resp, USER_UPDATED, HttpServletResponse.SC_OK);
            } else {
                writeResponse(resp, UNABLE_TO_UPDATE_EMPLOYEE, HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
