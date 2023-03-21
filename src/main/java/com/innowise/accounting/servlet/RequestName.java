package com.innowise.accounting.servlet;

import com.innowise.accounting.util.UrlPath;

import static com.innowise.accounting.util.HttpMethod.*;
import static com.innowise.accounting.util.UrlPath.*;

public enum RequestName {
    GET_EMPLOYEES(EMPLOYEES, GET),
    GET_EMPLOYEE_BY_ID(EMPLOYEE, GET),
    ADD_EMPLOYEE(EMPLOYEES, POST),
    UPDATE_EMPLOYEE_BY_ID(EMPLOYEES, PUT),
    DELETE_EMPLOYEE_BY_ID(EMPLOYEES, DELETE),
    LOGIN(UrlPath.LOGIN, GET),
    LOGOUT(UrlPath.LOGOUT, GET),
    BAD_REQUEST(ERROR, GET);

    private final String uri;
    private final String method;
    RequestName(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }
}
