package com.innowise.accounting.servlet.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.accounting.dto.EmployeeReadDto;
import com.innowise.accounting.service.EmployeeService;
import com.innowise.accounting.service.EmployeeServiceImpl;
import com.innowise.accounting.servlet.Request;
import com.innowise.accounting.util.IdParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class GetEmployeeByIdRequest implements Request {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeService service = new EmployeeServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = IdParser.parseId(req.getRequestURI());
        Optional<EmployeeReadDto> employeeDto = service.findById(id);

        if (employeeDto.isPresent()) {

        }

//        employeeDto.map(dto -> {
//                    String json = null;
//                    try {
//                        json = objectMapper.writeValueAsString(dto);
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    resp.setStatus(HttpServletResponse.SC_CREATED);
//                    PrintWriter out = null;
//                    try {
//                        out = resp.getWriter();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    resp.setContentType("application/json");
//                    resp.setCharacterEncoding("UTF-8");
//                    out.print(json);
//                    out.flush();
//                    return null;
//                })
//                .orElse(resp.setStatus(HttpServletResponse.SC_NOT_FOUND));

    }
}