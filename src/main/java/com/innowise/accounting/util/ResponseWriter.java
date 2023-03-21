package com.innowise.accounting.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.PrintWriter;

@UtilityClass
public class ResponseWriter {
    public static void writeResponse(HttpServletResponse resp, String text, int statusCode) throws IOException {
        resp.setStatus(statusCode);
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(text);
            out.flush();
        }
    }
}
