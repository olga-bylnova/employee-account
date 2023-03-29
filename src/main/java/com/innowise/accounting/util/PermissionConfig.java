package com.innowise.accounting.util;

import com.innowise.accounting.servlet.RequestName;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.innowise.accounting.servlet.RequestName.*;

@UtilityClass
public class PermissionConfig {
    public static final List<RequestName> ADMIN_REQUESTS = List.of(GET_EMPLOYEES,
        GET_EMPLOYEE_BY_ID,
        ADD_EMPLOYEE,
        UPDATE_EMPLOYEE_BY_ID,
        DELETE_EMPLOYEE_BY_ID,
        LOGOUT);
    public static final List<RequestName> USER_REQUESTS = List.of(GET_EMPLOYEES,
            GET_EMPLOYEE_BY_ID,
            LOGOUT);
    public static final List<RequestName> GUEST_REQUESTS = List.of(LOGIN);

    public static RequestName getRequestName(String uri, String method) {
        RequestName requestName = BAD_REQUEST;
        for (RequestName name : values()) {
            if (uri.contains(name.getUri())
                    && method.equals(name.getMethod())) {
                requestName = name;
                break;
            }
        }
        return requestName;
    }
}
