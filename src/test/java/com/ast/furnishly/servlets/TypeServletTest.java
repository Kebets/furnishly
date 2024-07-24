package com.ast.furnishly.servlets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class TypeServletTest {
    private TypeServlet typeServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        typeServlet = new TypeServlet();
    }

    @Test
    void testDoGet_AllTypes() throws Exception {
        when(request.getPathInfo()).thenReturn("/all");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        typeServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        String expectedJson = "[{\"id\": 1, \"name\": \"Chair\"}, {\"id\": 2, \"name\": \"Table\"}]";
        Assertions.assertEquals(expectedJson, stringWriter.toString().trim());
    }

    @Test
    void testDoGet_SingleType() throws Exception {
        when(request.getPathInfo()).thenReturn("/1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        typeServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        String expectedJson = "{\"id\": 1, \"name\": \"Chair\"}";

        Assertions.assertEquals(expectedJson, stringWriter.toString().trim());
    }

    @Test
    void testDoDelete_ValidType() throws Exception {
        when(request.getPathInfo()).thenReturn("/1"); // Replace with a valid type ID

        typeServlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Test
    void testDoDelete_InvalidType() throws Exception {
        when(request.getPathInfo()).thenReturn("/invalid"); // Replace with an invalid type ID

        typeServlet.doDelete(request, response);

        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
