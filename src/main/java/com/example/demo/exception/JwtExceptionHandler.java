package com.example.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtExceptionHandler {

    public void handle(HttpServletResponse response, Exception ex) throws IOException {

        HttpStatus status;
        String message;

        if (ex instanceof ExpiredJwtException) {
            status = HttpStatus.UNAUTHORIZED;
            message = "Token expired. Please login again.";
        } else if (ex instanceof SignatureException) {
            status = HttpStatus.UNAUTHORIZED;
            message = "Invalid token signature.";
        } else if (ex instanceof MalformedJwtException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Malformed token.";
        } else if (ex instanceof UnsupportedJwtException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Unsupported token.";
        } else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Token missing or empty.";
        } else {
            status = HttpStatus.UNAUTHORIZED;
            message = "Unauthorized request.";
        }

        response.setStatus(status.value());
        response.setContentType("application/json");

        new ObjectMapper().writeValue(
                response.getOutputStream(),
                Map.of(
                        "status", status.value(),
                        "error", status.getReasonPhrase(),
                        "message", message
                )
        );
    }
}
