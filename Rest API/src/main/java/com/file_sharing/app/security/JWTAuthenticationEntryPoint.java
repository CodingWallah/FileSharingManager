package com.file_sharing.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file_sharing.app.dto.ApiResponseMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

<<<<<<< HEAD
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationEntryPoint.class);
    private final ObjectMapper objectMapper;

    // Constructor for dependency injection of ObjectMapper
    public JWTAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Log the authentication exception
        logger.error("Unauthorized error: {}", authException.getMessage());

        // Set the response status to 401 Unauthorized when authentication fails
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Create a JSON response using the ApiResponseMessage class
        String json = objectMapper.writeValueAsString(ApiResponseMessage.builder()
                .success(false)
                .message("Authentication failed: " + authException.getMessage())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build());

        // Set the content type of the response to JSON
        response.setContentType("application/json");
        // Write the JSON response to the output stream
=======
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Set the response status to 401 Unauthorized when authentication fails.
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // Initialize ObjectMapper to convert the response into JSON format.
        ObjectMapper mapper = new ObjectMapper();
        // Create a JSON response using the ApiResponseMessage class.
        // This sets the success flag to false, adds the exception message, and sets the HTTP status.
        String json = mapper.writeValueAsString( ApiResponseMessage.builder()
                .success(false)
                .message(authException.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .build());
        // Set the content type of the response to JSON.
        response.setContentType("application/json");
        // Write the JSON response to the output stream.
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        response.getWriter().write(json);
    }
}
