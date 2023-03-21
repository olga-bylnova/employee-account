package com.innowise.accounting.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Request {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
