package com.innowise.accounting.filter;

import com.innowise.accounting.servlet.RequestName;
import com.innowise.accounting.util.ResponseMessage;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.innowise.accounting.util.AttributeName.*;
import static com.innowise.accounting.util.PermissionConfig.*;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

@WebFilter("/api/v1/*")
public class AuthorizationFilter implements Filter {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);

        RequestName requestName = getRequestName(request.getRequestURI(),
                request.getMethod());
        boolean accessGranted = GUEST_REQUESTS.contains(requestName);
        if (session != null) {
            String role = (String) session.getAttribute(ROLE);
            if (role != null) {
                switch (role) {
                    case ADMIN -> accessGranted = ADMIN_REQUESTS.contains(requestName);
                    case USER -> accessGranted = USER_REQUESTS.contains(requestName);
                }
            }
        }
        if (accessGranted) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            writeResponse(resp, ResponseMessage.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
