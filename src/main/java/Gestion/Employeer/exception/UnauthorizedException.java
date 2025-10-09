package Gestion.Employeer.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Accès non autorisé.");
    }
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}

