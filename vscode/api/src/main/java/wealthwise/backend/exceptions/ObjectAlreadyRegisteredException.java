package wealthwise.backend.exceptions;

public class ObjectAlreadyRegisteredException extends RuntimeException {
    public ObjectAlreadyRegisteredException(String message) {
        super(message);
    }
}
