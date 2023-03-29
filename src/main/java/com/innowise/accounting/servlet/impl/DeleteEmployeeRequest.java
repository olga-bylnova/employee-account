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

import static com.innowise.accounting.util.ResponseMessage.UNABLE_TO_DELETE_EMPLOYEE;
import static com.innowise.accounting.util.ResponseMessage.USER_DELETED;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class DeleteEmployeeRequest implements Request {
    private final EmployeeService service = new EmployeeServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = IdParser.parseId(req.getRequestURI());
            if (service.delete(id)) {
                writeResponse(resp, USER_DELETED, HttpServletResponse.SC_OK);
            } else {
                writeResponse(resp, UNABLE_TO_DELETE_EMPLOYEE, HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
