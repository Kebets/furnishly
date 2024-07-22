package com.ast.furnishly.servlets;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.services.TypeService;
import com.ast.furnishly.services.impl.TypeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * Servlet for managing type-related operations.
 */
@WebServlet(urlPatterns = {"/type/*"})
public class TypeServlet extends HttpServlet {
    private final TypeService typeService;
    private final ObjectMapper objectMapper;

    /**
     * Constructor for initializing the servlet.
     */
    public TypeServlet(){
        this.typeService = TypeServiceImpl.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Sets the JSON header for the HTTP response.
     *
     * @param response The HttpServletResponse object.
     */
    private static void setJsonHeader(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * Reads the JSON data from the request.
     *
     * @param request The HttpServletRequest object.
     * @return The JSON data as a string.
     * @throws IOException If an I/O error occurs.
     */
    private static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = request.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Handles the HTTP GET request.
     *
     * @param req  The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<TypeDto> typeDtoList = typeService.findAll();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(typeDtoList);
            } else {
                Long typeId = Long.parseLong(pathPart[1]);
                TypeDto typeDto = typeService.findById(typeId);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(typeDto);
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    /**
     * Handles the HTTP DELETE request.
     *
     * @param req  The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            Long id = Long.parseLong(pathPart[1]);
            if (typeService.delete(id)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    /**
     * Handles the HTTP POST request.
     *
     * @param req  The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);
        String responseAnswer = null;
        Optional<TypeDto> typeDtoResponse;
        try {
            typeDtoResponse = Optional.ofNullable(objectMapper.readValue(json, TypeDto.class));
            TypeDto typeDto = typeDtoResponse.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(typeService.save(typeDto));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect type Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    /**
     * Handles the HTTP PUT request.
     *
     * @param req  The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<TypeDto> typeDtoResponse;
        try {
            typeDtoResponse = Optional.ofNullable(objectMapper.readValue(json, TypeDto.class));
            TypeDto typeDto = typeDtoResponse.orElseThrow(IllegalArgumentException::new);
            typeService.update(typeDto);
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect type Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
