package com.ast.furnishly.servlets;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.services.FurnitureService;
import com.ast.furnishly.services.impl.FurnitureServiceImpl;
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
 * Servlet for managing furniture-related operations.
 */

@WebServlet(urlPatterns = {"/furniture/*"})
public class FurnitureServlet extends HttpServlet {
    private final FurnitureService furnitureService;
    private final ObjectMapper objectMapper;

    /**
     * Constructor for initializing the servlet.
     */
    public FurnitureServlet() {
        furnitureService = FurnitureServiceImpl.getInstance();
        objectMapper = new ObjectMapper();
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
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setJsonHeader(response);
        String responseAnswer = "";
        try {
            String[] pathPart = request.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<FurnitureDto> furnitureDtoList = furnitureService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(furnitureDtoList);
            } else {
                Long id = Long.parseLong(pathPart[1]);
                FurnitureDto furnitureDto = furnitureService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(furnitureDto);
            }
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Not found.";
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = response.getWriter();
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
            if (furnitureService.delete(id)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Not found.";
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
        Optional<FurnitureDto> furnitureDtoResponse;
        try {
            furnitureDtoResponse = Optional.ofNullable(objectMapper.readValue(json, FurnitureDto.class));
            FurnitureDto furnitureDto = furnitureDtoResponse.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(furnitureService.save(furnitureDto));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect furniture Object.";
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
        Optional<FurnitureDto> furnitureDtoResponse;
        try {
            furnitureDtoResponse = Optional.ofNullable(objectMapper.readValue(json, FurnitureDto.class));
            FurnitureDto furnitureDto = furnitureDtoResponse.orElseThrow(IllegalArgumentException::new);
            furnitureService.update(furnitureDto);
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Not found.";
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect manufacturer Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
