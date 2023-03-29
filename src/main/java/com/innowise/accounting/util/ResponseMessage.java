package com.innowise.accounting.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMessage {
    public static final String EMPLOYEE_NOT_FOUND = "employee not found";
    public static final String UNABLE_TO_DELETE_EMPLOYEE = "cannot delete employee";
    public static final String INVALID_CREDENTIALS = "invalid credentials";
    public static final String USER_LOGGED_IN = "user logged in successfully";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String UNABLE_TO_CREATE_EMPLOYEE = "cannot create employee";
    public static final String UNABLE_TO_FIND_EMPLOYEES = "cannot find any employees";
    public static final String UNABLE_TO_UPDATE_EMPLOYEE = "cannot update employee";
    public static final String USER_DELETED = "user was deleted";
    public static final String USER_UPDATED = "user was updated";
    public static final String USER_LOGGED_OUT = "user logged out";
}
