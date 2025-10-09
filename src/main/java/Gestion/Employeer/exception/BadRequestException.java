package Gestion.Employeer.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Requête invalide.");
    }
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

