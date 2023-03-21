package com.innowise.accounting.servlet;

import com.innowise.accounting.servlet.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Map;

import static com.innowise.accounting.servlet.RequestName.*;
import static com.innowise.accounting.servlet.RequestName.LOGIN;
import static com.innowise.accounting.servlet.RequestName.LOGOUT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestHandler {
    private static final Map<RequestName, Request> REQUEST_MAP =
            Map.ofEntries(
                    Map.entry(GET_EMPLOYEES, new GetEmployeesRequest()),
                    Map.entry(GET_EMPLOYEE_BY_ID, new GetEmployeeByIdRequest()),
                    Map.entry(ADD_EMPLOYEE, new AddEmployeeRequest()),
                    Map.entry(UPDATE_EMPLOYEE_BY_ID, new UpdateEmployeeRequest()),
                    Map.entry(DELETE_EMPLOYEE_BY_ID, new DeleteEmployeeRequest()),
                    Map.entry(LOGIN, new LoginRequest()),
                    Map.entry(LOGOUT, new LogoutRequest()),
                    Map.entry(BAD_REQUEST, new ErrorRequest())
            );
    private static final RequestHandler INSTANCE = new RequestHandler();

    public static RequestHandler getInstance() {
        return INSTANCE;
    }
    public void executeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestName key = BAD_REQUEST;
        for (RequestName name : values()) {
            if (req.getRequestURI().contains(name.getUri())
                    && req.getMethod().equals(name.getMethod())) {
                key = name;
                break;
            }
        }
        Request request = REQUEST_MAP.get(key);
        request.execute(req, resp);
    }
}
