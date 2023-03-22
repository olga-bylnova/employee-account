package com.innowise.accounting.servlet.impl;

import com.innowise.accounting.exception.ServiceException;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.IdParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class DeleteEmployeeRequest implements Request {
    private final EmployeeService service = new EmployeeServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = IdParser.parseId(req.getRequestURI());
            if (service.delete(id)) {
                writeResponse(resp, "User with id " + id + " was deleted", HttpServletResponse.SC_OK);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
