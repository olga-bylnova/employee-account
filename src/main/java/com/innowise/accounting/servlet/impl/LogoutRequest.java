package com.innowise.accounting.servlet.impl;

import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.ResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.innowise.accounting.util.AttributeName.ROLE;
import static com.innowise.accounting.util.ResponseMessage.USER_LOGGED_OUT;
import static com.innowise.accounting.util.ResponseWriter.writeResponse;

public class LogoutRequest implements Request {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(ROLE);
            session.invalidate();

            writeResponse(resp, USER_LOGGED_OUT, HttpServletResponse.SC_OK);
        }
    }
}
