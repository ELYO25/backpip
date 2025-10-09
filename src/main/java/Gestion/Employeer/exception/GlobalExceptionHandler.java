package Gestion.Employeer.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), req.getRequestURI(), null);
    }

    // 400 (personnalisée)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), req.getRequestURI(), null);
    }

    // 401
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(UnauthorizedException ex, HttpServletRequest req) {
        return build(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ex.getMessage(), req.getRequestURI(), null);
    }

    // 403 (Spring Security)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex, HttpServletRequest req) {
        return build(HttpStatus.FORBIDDEN, "FORBIDDEN", "Accès refusé.", req.getRequestURI(), null);
    }

    // 400 Validation @Valid (DTO, request body)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpServletRequest req) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        return build(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED",
                "Des champs sont invalides.", req.getRequestURI(), fieldErrors);
    }

    // 400 Validation @Validated (params, path vars)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex,
                                                              HttpServletRequest req) {
        Map<String, String> violations = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(v -> violations.put(String.valueOf(v.getPropertyPath()), v.getMessage()));

        return build(HttpStatus.BAD_REQUEST, "CONSTRAINT_VIOLATION",
                "Des contraintes de validation ont échoué.", req.getRequestURI(), violations);
    }

    // 400 JSON illisible / format invalide
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "MESSAGE_NOT_READABLE",
                "Le corps de la requête est invalide ou manquant.", req.getRequestURI(), null);
    }

    // 409 Conflit d'intégrité (clé unique, FK…)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "DATA_INTEGRITY_VIOLATION",
                "Conflit d'intégrité des données.", req.getRequestURI(), null);
    }

    // 500 (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
                "Une erreur interne est survenue.", req.getRequestURI(), null);
    }

    // ----------- utilitaire -----------
    private ResponseEntity<ApiError> build(HttpStatus status,
                                           String error,
                                           String message,
                                           String path,
                                           Map<String, String> subErrors) {
        ApiError body = new ApiError(
                OffsetDateTime.now().toString(),
                status.value(),
                error,
                message,
                path,
                subErrors
        );
        return ResponseEntity.status(status).body(body);
    }

    // Réponse d'erreur unifiée (pas besoin de créer un fichier séparé)
    public static class ApiError {
        public String timestamp;
        public int status;
        public String error;
        public String message;
        public String path;
        public Map<String, String> subErrors; // ex: { "champ": "erreur" }

        public ApiError(String timestamp, int status, String error, String message, String path,
                        Map<String, String> subErrors) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
            this.subErrors = (subErrors == null || subErrors.isEmpty()) ? null : subErrors;
        }
    }
}

