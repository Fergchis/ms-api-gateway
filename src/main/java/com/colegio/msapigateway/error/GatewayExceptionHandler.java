package com.colegio.msapigateway.error;

import jakarta.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GatewayExceptionHandler {

    @ExceptionHandler({ResourceAccessException.class, ConnectException.class})
    public ResponseEntity<Map<String, Object>> handleUpstreamUnavailable(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = Map.of(
            "timestamp", Instant.now().toString(),
            "status", HttpStatus.SERVICE_UNAVAILABLE.value(),
            "error", "Service Unavailable",
            "message", "Upstream service is unavailable",
            "path", request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }
}