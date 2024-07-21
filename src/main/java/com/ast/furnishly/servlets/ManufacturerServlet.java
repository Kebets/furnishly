package com.ast.furnishly.servlets;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.services.ManufacturerService;
import com.ast.furnishly.services.impl.ManufacturerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/manufacturer/*"})
public class ManufacturerServlet extends HttpServlet {
    private final ManufacturerService manufacturerService;
    private final ObjectMapper objectMapper;

    public ManufacturerServlet(){
        this.manufacturerService = ManufacturerServiceImpl.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    private static void setJsonHeader(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = request.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setJsonHeader(response);

        String responseAnswer = "";
        try {
            String[] pathPart = request.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<ManufacturerDto> manufacturerDtoList = manufacturerService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(manufacturerDtoList);
            } else {
                Long id = Long.parseLong(pathPart[1]);
                ManufacturerDto manufacturerDto = manufacturerService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(manufacturerDto);
            }
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

}
